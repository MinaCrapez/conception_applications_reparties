import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServeurFTP {
    /**
     * ServeurFTP is a class representing a server FTP with its caracteristics and some commands
     * 09/01/23
     * Mina Crapez - M1 MIAGE
     */

    protected Socket socket; // a socket to access the server
    private ArrayList<ServeurFTP> threads; // the list of threads, a thread is a connexion by a user
    protected int port; // the integer using as a port to connect the server
    protected Boolean isOpen; // a boolean representing if the server is closed or opened
    protected Boolean logIn; // a boolean representing if a client is connected or not
    protected BufferedReader br; // a bufferedReader to read the command enter by the user
    protected PrintWriter printer;// a printer to print the result of the server
    

    public ServeurFTP(Socket socket, ArrayList<ServeurFTP> threads, int port) throws IOException{
        this.socket = socket;
        this.threads = threads; 
        this.port = port; 
        this.isOpen = true;
        this.logIn = false; 

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        OutputStream os = socket.getOutputStream();
        this.br = new BufferedReader(isr);

        this.printer = new PrintWriter(os,true);
    }

    public static void main(String[] args) throws IOException{
        // initialisation
        ArrayList<ServeurFTP> threads = new ArrayList<>(); 
        int port = 1024;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        // connexion
        ServerSocket serverSocket = new ServerSocket(port); 
        Socket socket = serverSocket.accept(); 

        ServeurFTP server = new ServeurFTP(socket,threads, port);

        threads.add(server); 

        server.start(); 

        // on ferme les connexion
        serverSocket.close();
    }

    /**
     * write the answer of the server
     * @param message the message the server as to write
     */
    public void write (String message) {
        printer.println(message);
    }

    /**
     * while the server is open, a user can write a command and this function tell the programm how to respond
     * @throws IOException
     */
    public void start() throws IOException {
        // pour tous les threads :
        for(int i = 0; i < this.threads.size();i++){
            write("Connect to host Linux, port "+port+",establishing control connections. <---- 220 Service ready <CRLF>.");

            String username = null;
            while (isOpen) { 
                String msg = br.readLine();
                if (msg.startsWith("quit")) {
                    close();
                }
                else if(msg.startsWith("username")){
                    username = authentification(msg);
                }
                else if(msg.startsWith("password")){
                    motDePasse(msg, username);
                }

                else if(msg.startsWith("get")){
                    commandGet(msg);
                }
                else if(msg.startsWith("put")){
                    commandPut(msg);
                }
                else if(msg.startsWith("cd")){
                    commandCd(msg);
                }
                else if(msg.startsWith("dir")){
                    commandDir(msg);
                }
                else {
                    write ("<CRLF>----> <---- 502 command not implemented <CRLF>.");
                }
            } 
        }

    } 

    /**
     * while a username is given, the server will answer
     * @param message the username given by a user
     * @return the answer of the programme with a username given
     * @throws IOException
     */
    public String authentification(String message) throws IOException {
        String res = null;
        String[] msgSplit = message.split(" ");
        String username = msgSplit[1];
        String returnMsg = "error <CRLF>----><----430 inexisting username<CRLF>.";

        for (Identification ident : Identification.values()) {
            if (ident.getUsername().equals(username)) {
                returnMsg = "USER "+username+" <CRLF>----> <---- 331 User name ok, need password<CRLF>.";
                res = username;
            }
        }
        write (returnMsg);
        return res;
    } 

    /**
     * while a password is given, the server will answer
     * @param message the password given by a user
     * @return the answer of the programme with a password given
     * @throws IOException
     */
    public void motDePasse(String message, String username) throws IOException {
        String[] msgSplit = message.split(" ");
        String password = msgSplit[1];

        String returnMsg = "error <CRLF>----><----430 wrong password";

        for (Identification ident : Identification.values()) {
            if(username != null && ident.getUsername().equals(username) && ident.getMotDePasse().equals(password)) {
                returnMsg = "PASS "+password+"<CRLF>----><---- 230 User logged in<CRLF>.";
                logIn = true;
            }
        }
        write(returnMsg);     
    }


    /**
     * the server is closed when the command "quit" is given by a user
     * @throws IOException
     */
    public void close() throws IOException {
        write("<CRLF>----> <---- 221 logout <CRLF>.");
        printer.close();
        br.close();
        socket.close(); 
        isOpen = false; 
    }

    public void commandGet(String message) throws IOException {
        write("A CORRIGER");
        // copier un fichier message en local

        // message si tout va bien : 250, action sur le fichier effectuée avec succès
        // sinon :  451 action requise arreté, erreur local dans le traitement

        // 532 si on s'est pas connecté avant 

        String[] msgSplit = message.split(" ");
        String remoteFile = msgSplit[1];
        String localFile = "remoteFile";
        if(msgSplit.length > 1) {
            localFile = msgSplit[2];
        }
        File src = new File(remoteFile);
        File dest = new File(localFile);

        InputStream is = new FileInputStream(src);
        OutputStream os  = new FileOutputStream(dest);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) > 0) {
            os.write(buffer, 0, len);
        }
        is.close();
        os.close();


    }


    public void commandPut(String message) {
        write("TO DO");
    }


    public void commandCd(String message) {
        write("TO DO");
    }


    public void commandDir(String message) {
        write("TO DO");
    }

}



