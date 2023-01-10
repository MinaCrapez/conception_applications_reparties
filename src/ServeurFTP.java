import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import command.CommandUsername;
import tool.Identification;
import command.CommandPassword;
import command.CommandGet;
import command.CommandQuit;
import command.Command;

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

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
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
        socket.close();
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
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     */
    public void start() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // pour tous les threads :
        for(int i = 0; i < this.threads.size();i++){
            write("Connect to host Linux, port "+port+",establishing control connections. <---- 220 Service ready <CRLF>.");

            String username = "";
            while (isOpen) { 
                String msg = br.readLine();

                String[] msgSplit = msg.split(" ");
                String commandAsk = msgSplit[0];
                String commandAskMaj=commandAsk.replaceFirst(".",(commandAsk.charAt(0)+"").toUpperCase());
                
                // gestion des commande sans paramètre
                String message = "";
                if(msgSplit.length > 1) {
                    message = msgSplit[1];
                }
        
                String nameClass = "Command"+commandAskMaj;
                try{
                Class<?> commandGiven = Class.forName("command."+nameClass);
                Command Command = (Command) commandGiven.getConstructor().newInstance();

                // test du password correspondant au username 
                if(nameClass.equals("CommandUsername")) {
                    write(Command.run(message));
                    username = message;
                }
                else if(nameClass.equals("CommandPassword")) {
                    write(Command.run(username+" "+message));
                }
                else if (nameClass.equals("CommandQuit")) {
                    write (Command.run(message));
                    close();
                    return;
                }
                else {
                    write (Command.run(message));
                }
                }
                catch (ClassNotFoundException e) {
                    write ("<CRLF>----> <---- 502 command not implemented <CRLF>.");
                }
            }
        }
    }

    /**
     * the server is closed when the command "quit" is given by a user
     * @throws IOException
     */
    public void close() throws IOException {
        printer.close();
        br.close();
        Thread.currentThread().interrupt();
        isOpen = false; 
    }
                

}



