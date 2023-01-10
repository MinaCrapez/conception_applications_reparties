package command;

import java.io.*;

public class CommandGET extends Command{
    /**
     * class representing the command get for the FTP Server
     * 10/01/23
     * Mina Crapez - M1 MIAGE
     */

    public CommandGET() {
        super();
    }

    /**
     * copy a file
     * @param message the file we want to copy and, not necessary, the new file we want (remoteFile if not given)
     * @throws IOException
     */
    public String run(String file){
        String[] msgSplit = file.split(" ");
        String remoteFile = msgSplit[0];
        String localFile = "remoteFile";
        if(msgSplit.length > 1) {
            localFile = msgSplit[0];
        } 
        try {
        
            File src = new File(remoteFile);
            File dest = new File(localFile);

            InputStream is = new FileInputStream(src);
            OutputStream os  = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            is.close();
            os.close();

            return("<CRLF>----> <---- 250 the file as well been copied <CRLF>.");
        }
        catch (IOException e) {
            return("<CRLF>----> <---- 451 action stopped. local error in the traitement<CRLF>.");
        }
    }

}