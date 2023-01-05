import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServeurFTP {



    private Socket socket;
    private ArrayList<ServeurFTP> threads;
    private int port;

    public ServeurFTP(Socket socket, ArrayList<ServeurFTP> threads, int port){
        this.socket = socket;
        this.threads = threads;
        this.port = port;
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

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
    
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        DataOutputStream dos = new DataOutputStream(os);

        // pour tous les threads :
        for(int i = 0; i < this.threads.size();i++){
            dos.writeBytes("Connect to host linux port "+this.port+ " establishing control connections.<---- 220 Service ready <CRLF>.");
            
            String msg = br.readLine();
            Mdp mdp;
            if(mdp.getMdp().containsValue(msg)){
                dos.
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
