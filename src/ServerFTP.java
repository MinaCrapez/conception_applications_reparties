import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import tool.Identification;

public class ServerFTP{

     /**
     * ServerFTP is a class representing a server FTP with its caracteristics and some commands
     * 09/01/23
     * Mina Crapez - M1 MIAGE
     */

    protected Socket socket; // a socket to access the server
    protected Socket socketEnvoie; // a socket to get data
    protected int port; // the integer using as a port to connect the server
    protected Boolean isOpen; // a boolean representing if the server is closed or opened
    protected Boolean logIn; // a boolean representing if a client is connected or not
    protected String username; // le username connecté
    protected String directory;
    protected InputStream is; // permet d'agir sur les données d'entrée
    protected OutputStream os; // permet d'agir les données de sortie
    protected InputStreamReader isr; // permet de lire les données dentrée
    protected BufferedReader br; // permet de lire les données
    protected DataOutputStream dos; // permet de representer les données à ecrire

    public ServerFTP(Socket socket, int port) throws IOException{
        this.socket = socket;
        this.port = port; 
        this.isOpen = true;
        this.logIn = false; 
        this.directory = "home/";

        is = socket.getInputStream();
        os = socket.getOutputStream();

        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        dos = new DataOutputStream(os);
    }

    public static void main(String[] args) throws IOException {

        int port = 1024;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        // connexion
        ServerSocket serverSocket = new ServerSocket(port); 
        Socket socket = serverSocket.accept(); 
        ServerFTP server = new ServerFTP(socket, port);
        server.start();

        serverSocket.close();
        socket.close();
    }

    /*
     * start the server, a user can use some implemented commands 
     * @throws IOException
    */
    public void start() throws IOException {

        System.out.println("Connect to host Linux, port "+port+",establishing control connections.");
        dos.writeBytes("220 Service ready \r\n");
        creationDepot(); // permet la creation de nos depots local
        while (isOpen) {
            String msg = br.readLine();
            System.out.println(msg);

            String[] msgSplit = msg.split(" ");
            String commandAsk = msgSplit[0];
                
            // gestion des commande sans paramètre
            String message = "";
            if(msgSplit.length > 1) {
                message = msgSplit[1];
            }     
            if(commandAsk.startsWith("USER")) {
                commandUser(message);
                username = message;
            }
            else if(commandAsk.startsWith("PASS")) {
                commandPass(username+" "+message);
                logIn = true;
                directory = directory+username;
            }
            else if(commandAsk.startsWith("SYST")) {
                commandSyst();
            }
            else if(commandAsk.startsWith("QUIT")) {
                commandQuit();
                return;
            }
            else if(commandAsk.startsWith("PORT")) {
                commandPort(message);
                String portSocketEnvoie = message;

                String[] portSplit = portSocketEnvoie.split(",");
        
                String h1 = portSplit[0];
                String h2 = portSplit[1];
                String h3 = portSplit[2];
                String h4 = portSplit[3];
                String p1 = portSplit[4];
                String p2 = portSplit[5];
        
                int port_TCP = Integer.parseInt(p1) * 256 + Integer.parseInt(p2);
                String ipEnvoie = h1+"."+h2+"."+h3+"."+h4;

                socketEnvoie = new Socket(ipEnvoie, port_TCP);
        
            }
            else if(commandAsk.startsWith("RETR")) {
                commandRetr(message);
            }
            else if(commandAsk.startsWith("STOR")) {
                commandStor(message);
            }
            else if(commandAsk.startsWith("CWD")) {
                commandCwd(message);
            }
            else if(commandAsk.startsWith("LIST")) {
                commandList(message);
            }
            else {
                dos.writeBytes("502 command not implemented\r\n");
            }
        }

    }

    /**
     * this function permits to the server to answer to the USER command for authentification
     * @param username the username we want to connect
     * @throws IOException
     */
    public void commandUser(String username) throws IOException {
        String returnMsg = "430 inexisting username<CRLF>.\r\n";
        for (Identification ident : Identification.values()) {
            if (ident.getUsername().equals(username)) {
                System.out.println("user login with username :"+username+". Need password");
                returnMsg = "331 User name ok, need password\r\n";
            }
        } 
        dos.writeBytes(returnMsg);
    }

    /**
     * this function permits to the server to answer to the PASS command for authentification
     * @param usernamePassword the username and password a user is trying to connect
     * @throws IOException
     */
    public void commandPass(String usernamePassword) throws IOException {
        String returnMsg = "430 wrong password\r\n";
        String[] usernamePasswordSplit = usernamePassword.split(" ");
        String usrname = usernamePasswordSplit[0];
        String password = usernamePasswordSplit[1];

        for (Identification ident : Identification.values()) {
            if(username != null && ident.getUsername().equals(usrname) && ident.getMotDePasse().equals(password)) {
                System.out.println("user login with username "+usrname+" and password "+password);
                returnMsg = "230 User logged in \r\n";
            }
        } 
        dos.writeBytes(returnMsg); 
    }

    /**
     * this function permits to the server to answer to the SYST command
     * @throws IOException
     */
    public void commandSyst()  throws IOException {
        System.out.println("Envoi de l'OS");
        dos.writeBytes("215 UNIX\r\n");
    }

     /**
     * the server is closed when the command "QUIT" is given by a user
     * @throws IOException
     */
    public void commandQuit() throws IOException {
        System.out.println("deconnexion");
        dos.writeBytes("221 Deconnexion\r\n");

        logIn = false;
        isr.close();
        br.close();
        dos.close();
        is.close();
        os.close();
        socket.close();

    }
    /**
     * this function permits to the server to answer to the PORT command 
     * @param port the port given by the server
     * @throws IOException
     */
    public void commandPort(String port) throws IOException {
        System.out.println(port);
        dos.writeBytes("200 Command okay.\r\n");
    }

    /**
     * this function permits to the server to answer to the Retr command for download a file
     * @param file the file to download
     * @throws IOException
     */
    public void commandRetr(String file) throws IOException {
        File src = new File(directory+"/"+file);
        if (src.exists()) {
            System.out.println("le fichier "+file+" dans le dépot personnel home/"+username+" a été trouvé");
            System.out.println("telechargement de "+file);
            dos.writeBytes("150 File status okay; about to open data connection.\r\n");
            commandGet(src);
        }
        else {
            dos.writeBytes("450 the distant file is not available.\r\n");
        }
    }

    /**
     * this function permits to the server to answer to the GET command to get a file on local
     * @param file the file we want to get
     * @throws IOException
     */
    public void commandGet(File src) throws IOException {
        InputStream is = new FileInputStream(src);
        OutputStream outputEnvoie = socketEnvoie.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) > 0) {
            outputEnvoie.write(buffer, 0, len);
        } 
        is.close();
        outputEnvoie.close();
        dos.writeBytes("226 Closing data connection.\r\n"); 
    }

    /*
     * create the inexistant distant depot if a new user is create
     */
    public void creationDepot() {
        for (Identification ident : Identification.values()) {
            File src = new File("home/"+ident.getUsername());
            src.mkdir();
        }
    }

    /**
     * this function permits to the server to answer to the STOR command to store a file on a distant deposit
     * @param file the file to store
     * @throws IOException
     */
    public void commandStor(String src) throws IOException {
        File destination = new File(directory+"/"+src);
        dos.writeBytes("150 File status okay; about to open data connection.\r\n");

        commandPut(destination);
    }

    /**
     * this function permits to the server to answer to the PUT command to put a local file on distant 
     * @param file the file we want to put
     * @throws IOException
     */
    public void commandPut(File file) throws IOException {
        InputStream outputEnvoie = socketEnvoie.getInputStream();
        OutputStream os = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = outputEnvoie.read(buffer)) > 0) {
            os.write(buffer, 0, len);
        } 
        os.close();
        outputEnvoie.close();
        dos.writeBytes("226 Closing data connection.\r\n"); 
    }

    /**
     * answer to the Cwd command, move to another directory
     * @param repertoire the new directory we want to go
     * @throws IOException
     */
    public void commandCwd(String repertoire) throws IOException{
        String reponse = "550 file unavailable\r\n";
        if (repertoire.equals("../")) {
            reponse = "200 Command okay.\r\n";
            // on revient au directory precedent
            String[] dir = directory.split("/");
            if (dir.length > 1) {
                directory = "home";
                for (int i = 1; i< dir.length - 1; i ++) {
                    directory = directory+"/"+dir[i]; 
                  }
            }
        }
        else {
            File dir  = new File(directory);
            File[] listeDesFichiers = dir.listFiles();
            for(File item : listeDesFichiers){
                if (repertoire.equals(item.getName())) {
                    directory = directory+"/"+repertoire; 
                    reponse = "200 Command okay.\r\n";
                }
            }
        }
        dos.writeBytes(reponse);
    }

    /**
     * answer to the dir command, list all the file on the directory
     * @param repertoire the directory we want to observ
     * @throws IOException
     */
    public void commandList(String repertoire) throws IOException {
        File dir  = new File(directory);
        listerLesFichiers(dir);
        dos.writeBytes("200 Command okay.\r\n");
    }

    /**
     * list all the file on the directory
     * @param dir the file we want to observ
     * @throws IOException
     */
    public void listerLesFichiers(File dir) {
        File[] liste = dir.listFiles();
        if (liste.length != 0) {
            for(File item : liste){
                if(item.isFile()){ 
                    System.out.println("fichier: "+item.getName()); 
                } 
                else if(item.isDirectory()){
                    System.out.println("répertoire: "+item.getName()); 
                }
            }
        }
        else {
            System.out.println("le fichier est vide");
        }
    }

}
