import java.io.*;
import java.net.*;

public class ClientFTP {

    /*
     * client du serveur FTP de Camille Poirier
     */

    private static int port;
    private static String ip;

    private InputStreamReader isr;
    private BufferedReader br;
    private DataOutputStream dos;

    public ClientFTP(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        dos = new DataOutputStream(os);
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
        // identification
        String str = "";
        str = br.readLine(); 
        System.out.println(str);
        System.out.println("Name("+ip+"): poipoi");
        dos.writeBytes("USER poipoi\r\n");
        str = br.readLine(); 
        System.out.println(str);
        System.out.println("Password: poipoi");
        dos.writeBytes("PASS poipoi\r\n");
        str = br.readLine(); 
        System.out.println(str);
        dos.writeBytes("SYST\r\n");
        System.out.println("Remote system type is UNIX");
        dos.writeBytes("FEAT\r\n");
        str = br.readLine(); 
        System.out.println(str); 
        dos.writeBytes("TYPE I\r\n");
        str = br.readLine(); 
        System.out.println(str); 
        System.out.println("Using binary mode to transfer files");
        // dir
        System.out.println("> dir");
        dos.writeBytes("LIST\r\n");
        str = br.readLine(); 
        System.out.println(str);
        dos.writeBytes("PATH FOR LIST :poipoi\r\n");
        str = br.readLine(); 
        System.out.println(str);
        // cd
        System.out.println("> cd dir");
        dos.writeBytes("CWD dir\r\n");
        str = br.readLine(); 
        System.out.println(str);
        // get
        System.out.println("> get");
        System.out.println("(remote-file) : test3.txt");
        System.out.println("(local-file) : local2.txt");
        System.out.println("local : local2.txt remote: test3.txt");
        dos.writeBytes("EPSV\r\n");
        str = br.readLine(); 
        System.out.println(str);
        dos.writeBytes("RETR local2.txt\r\n");
        str = br.readLine(); 
        System.out.println(str);
        dos.writeBytes("path : poipoi/test3.txt\r\n");
        str = br.readLine(); 
        System.out.println(str);  
        // quit
        System.out.println("> quit");
        dos.writeBytes("QUIT\r\n");
        dos.writeBytes("Deconnexion\r\n");
        str = br.readLine(); 
        System.out.println(str);
    }

}

