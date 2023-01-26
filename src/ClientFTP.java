import java.io.*;
import java.net.*;

public class ClientFTP {

    /*
     * client du serveur FTP de Camille Poirier
     */

    private boolean isLoggin; // permet de savoir si le client est connecté ou non
    private String username; // le username du client
    private String password; // le password du client

    private static int port;
    private static String ip;

   // private static ServerSocket serverSocket;
    private String directory;
    private Socket socket;

    private InputStreamReader isr;
    private BufferedReader br;
    private DataOutputStream dos;

    public ClientFTP(Socket socket) throws IOException {
        this.socket = socket;
        this.directory = "/";

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        dos = new DataOutputStream(os);
}

    public Boolean getIsLoggin() {
        return this.isLoggin;
    }

    public void setIsLoggin(Boolean newLoggin) {
        this.isLoggin = newLoggin;
    }

    public static void main(String[] args) throws IOException {
        ip = args[0];
        port = Integer.parseInt(args[1]);
        Socket socket = new Socket(ip,port);
        ClientFTP client = new ClientFTP(socket);
        System.out.println("Connected to "+ args[0]);
        client.run();
        
    }

    public void run() throws IOException {
        String str = br.readLine(); 
        System.out.println(str);
        System.out.println("Name("+ip+"):");
        username = br.readLine();
        System.out.println("USER "+username);
    }

}
