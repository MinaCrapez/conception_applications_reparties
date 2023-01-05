import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;


public class ServeurFTP {


    private DataOutputStream dos;
    private BufferedReader br;
    private InputStreamReader isr;
    private Socket socket;
    private ArrayList<ServeurFTP> threads;
    private int port;
    private boolean isOpen;

    Map<String, String> mdp;

    public ServeurFTP(Socket socket, ArrayList<ServeurFTP> threads, int port){
        this.socket = socket;
        this.threads = threads;
        this.port = port;
        this.isOpen = true;
        this.mdp = new HashMap<String, String>();
        this.mdp.put("test","test");

        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
    
            this.isr = new InputStreamReader(is);
            this.br = new BufferedReader(isr);
            this.dos = new DataOutputStream(os);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void write (String message) throws IOException {
        dos.writeBytes(message);
    }

    public static void main(String[] args) throws IOException{
        // initialisation
        ArrayList<ServeurFTP> threads = new ArrayList<>(); 
        int port = Integer.parseInt(args[0]);
        // connexion
        ServerSocket serverSocket = new ServerSocket(port); 
        Socket socket = serverSocket.accept(); 
       
        ServeurFTP server = new ServeurFTP(socket,threads,port);
        
        threads.add(server); 
        server.start(); 

        // on ferme les connexion
        serverSocket.close();
        socket.close(); 
    }

    public void start() throws IOException {
        for(int i = 0; i < this.threads.size();i++){
            dos.writeBytes("Connect to host linux port "+this.port+ " establishing control connections.<---- 220 Service ready <CRLF>.");
            while(isOpen) {
                String msg = br.readLine();
                this.authentification(msg);
                this.motDePasse(msg);
                }
           }
        } 
     

    public void authentification(String message) throws IOException {
        if(message.contains("username")){
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
        if(message.contains("password")){
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

/*
-----------------


var serv  = new ServerSocket(4242);

while (true) {
    Socket s = serv.accept();

    InputStream is = s.getInputStream();
    OutputStream os = s.getOutputStream();


    InputStreamReader isr = new inputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    DataOutputStream dos = new DataOutputStream(os);

    doc.writeBytes("connected to localHost");
    
} */
