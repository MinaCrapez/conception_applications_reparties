import java.io.BufferedReader;
import java.io.DataOutputStream;
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
        Map<String,String> logs = new HashMap<>();
        logs.put("test", "test");
        logs.put("mina", "mdp");

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
       // System.out.println("user login with username :"+username+". Need password");
        //dos.writeBytes("331 User name ok, need password\r\n");

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

    public void commandSyst() {
        
    }

}
