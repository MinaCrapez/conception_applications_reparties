import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServeurFTP {

    protected Socket socket;
    protected int port;
    protected Boolean isOpen;
   // private ArrayList<ServeurFTP> threads;
    protected BufferedReader br;
    protected PrintWriter printer;
    protected Map<String, String> mdp;
    

    public ServeurFTP(Socket socket, int port) throws IOException{
        this.socket = socket;
        // this.threads = threads;
        this.port = port;
        this.isOpen = true;

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
        //ArrayList<ServeurFTP> threads = new ArrayList<>(); 
        int port = 1024;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        // connexion
        ServerSocket serverSocket = new ServerSocket(port); 
        Socket socket = serverSocket.accept(); 

        ServeurFTP server = new ServeurFTP(socket,port);

        //threads.add(server); 
        server.start(); 

        // on ferme les connexion
        serverSocket.close();
        socket.close(); 
    }

    public void write (String message) {
        printer.println(message);
    }

    public void start() throws IOException {
        // pour tous les threads :
        //  for(int i = 0; i < this.threads.size();i++){
        write("Connect to host Linux, port "+port+",establishing control connections. <---- 220 Service ready <CRLF>.");

        while (true) { // !! ne marche pas !!
            String msg = br.readLine();
            authentification(msg);
            motDePasse(msg);
        } 

    } 

    public void authentification(String message) throws IOException {
        if(message.startsWith("username")){
            String user = message.replace("username ","");
            if(this.mdp.containsKey(user)) {
                write("USER"+user+"<CRLF>----> <---- 331 User name ok, need password<CRLF>.");
            }
            else{
                write("TROUVER LE CODE ERREUR");
            }
        }
    } 

    public void motDePasse(String message) throws IOException {
        if(message.startsWith("password")){
            String mdp = message.replace("password ","");
            if(this.mdp.containsValue(mdp)) {
                write("PASS"+mdp+"<CRLF>----><---- 230 User logged in<CRLF>.");
            }
            else{
                write("TROUVER LE CODE ERREUR");
            }
        }
    }
}



