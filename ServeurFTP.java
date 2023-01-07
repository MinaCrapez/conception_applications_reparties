import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServeurFTP {

    protected Socket socket;
    protected int port;
    protected Boolean isOpen;
    private ArrayList<ServeurFTP> threads;
    protected BufferedReader br;
    protected PrintWriter printer;
    protected Map<String, String> mdp;
    protected Boolean logIn;
    

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

        this.mdp = new HashMap<String, String>();
        this.mdp.put("test","test");
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

    public void write (String message) {
        printer.println(message);
    }

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
                if(msg.startsWith("username")){
                    username = authentification(msg);
                }
                if(msg.startsWith("password")){
                    motDePasse(msg, username);
                }
            } 
        }

    } 
    public void close() throws IOException {
        write("on ferme");
        printer.close();
        br.close();
        socket.close(); 
        isOpen = false;

        
    }

    public String authentification(String message) throws IOException {
        String res;
        String[] msgSplit = message.split(" ");
        String username = msgSplit[1];
        if(this.mdp.containsKey(username)) {
            write("USER "+username+" <CRLF>----> <---- 331 User name ok, need password<CRLF>.");
            res = username;
        }
        else{
            write("TROUVER LE CODE ERREUR");
            res = null;
        }
        return res;
    } 

    public void motDePasse(String message, String username) throws IOException {
        if(message.startsWith("password")){
            String[] msgSplit = message.split(" ");
		    String password = msgSplit[1];
            if(username != null && this.mdp.get(username).equals(password)) { //si le mot de passe correspond au username rentré
                write("PASS "+password+"<CRLF>----><---- 230 User logged in<CRLF>.");
                logIn = true;
            }
            else{
                write("error <CRLF>----><----530 wrong password");
            } 
        } 
    }
}



