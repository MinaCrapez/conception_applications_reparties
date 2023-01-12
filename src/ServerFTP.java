import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tool.Identification;

public class ServerFTP{

    protected Socket socket;
    protected Socket socketEnvoie;
    protected int port;
    protected Boolean isOpen;
    protected Boolean logIn;
    protected InputStream is;
    protected OutputStream os;
    protected InputStreamReader isr;
    protected BufferedReader br;
    protected DataOutputStream dos;

    public ServerFTP(Socket socket, int port) throws IOException{
        this.socket = socket;
        this.port = port; 
        this.isOpen = true;
        this.logIn = false; 

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

    public void start() throws IOException {
    
        String username = "";

        System.out.println("Connect to host Linux, port "+port+",establishing control connections.");
        dos.writeBytes("220 Service ready \r\n");

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
                String ip = h1+h2+h3+h4;

                ServerSocket serverSocketEnvoie = new ServerSocket(port_TCP); 
                socketEnvoie = serverSocketEnvoie.accept();
        
            }
            else if(commandAsk.startsWith("RETR")) {
                commandPort(message);
            }
            else {
                dos.writeBytes("502 command not implemented\r\n");
            }
        }

    }

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

    public void commandPass(String usernamePassword) throws IOException {
        String returnMsg = "430 wrong password\r\n";
        String[] usernamePasswordSplit = usernamePassword.split(" ");
        String username = usernamePasswordSplit[0];
        String password = usernamePasswordSplit[1];

        for (Identification ident : Identification.values()) {
            if(username != null && ident.getUsername().equals(username) && ident.getMotDePasse().equals(password)) {
                System.out.println("user login with username "+username+" and password "+password);
                returnMsg = "230 User logged in \r\n";
            }
        } 
        dos.writeBytes(returnMsg); 
    }

    public void commandSyst()  throws IOException {
        System.out.println("Envoi de l'OS");
        dos.writeBytes("215 UNIX\r\n");
    }

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

    public void commandPort(String port) throws IOException {
        System.out.println(port);
        //port_TCP = p1 * 256 + p2
        // (h1,h2,h3,h4,p1,p2)
        String[] portSplit = port.split(",");
        
        String h1 = portSplit[0];
        String h2 = portSplit[1];
        String h3 = portSplit[2];
        String h4 = portSplit[3];
        String p1 = portSplit[4];
        String p2 = portSplit[5];

        int port_TCP = Integer.parseInt(p1) * 256 + Integer.parseInt(p2);
        String ip = h1+h2+h3+h4;

        System.out.println("LE PORT DE LA SOCKET SERA "+ port_TCP);
        System.out.println("LIP DE LA SOCKET SERA "+ ip);

        dos.writeBytes("200 Command okay.\r\n");
        // creer une nouvelle socket qui se connecte à port_TCP avec l'ip h1 h2 h3 h4
    }

    public void commandRetr(String file) {

    }

   /*  public void commandGet(String file) throws IOException {
        String[] msgSplit = file.split(" ");
        String remoteFile = msgSplit[0];
        String localFile = "remoteFile";
        if(msgSplit.length > 1) {
            localFile = msgSplit[0];
        } 
        try {
            File src = new File("test/"+remoteFile);

            InputStream is = new FileInputStream(src);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            } 
            is.close();
            //os.close();

            dos.writeBytes("250 the file as well been copied");
        }
        catch (IOException e) {
            dos.writeBytes("451 action stopped. local error in the traitement");
        }
    } */

}
