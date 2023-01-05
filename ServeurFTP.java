import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurFTP {

    private Socket socket;
    private ArrayList<ServeurFTP> threads;

    public ServeurFTP(Socket socket, ArrayList<ServeurFTP> threads){
        this.socket = socket;
        this.threads = threads;
    }

    public static void main(String[] args) throws IOException{
        // initialisation
        ArrayList<ServeurFTP> threads = new ArrayList<>(); 

        // connexion
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0])); 
        Socket socket = serverSocket.accept(); 
       
        ServeurFTP server = new ServeurFTP(socket,threads);
        
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
            dos.writeBytes("connected to localHost \r\n");
            dos.writeBytes("Connect to host S, port L,\n establishing control connections.\n <---- 220 Service ready <CRLF>.");
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
