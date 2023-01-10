package command;

import tool.Identification;

public class CommandPassword extends Command{
    /**
     * class representing the command password for the FTP Server
     * 10/01/23
     * Mina Crapez - M1 MIAGE
     */

    public CommandPassword() {
        super();
    }

     /**
     * while a password is given, the server will answer
     * @param message the password given by a user
     * @return the answer of the programme with a password given
     * @throws IOException
     */
    public String run(String usernamePassword){
        String returnMsg = "error <CRLF>----><----430 wrong password <CRLF>.";
        String[] usernamePasswordSplit = usernamePassword.split(" ");
        String username = usernamePasswordSplit[0];
        String password = usernamePasswordSplit[1];

        for (Identification ident : Identification.values()) {
            if(username != null && ident.getUsername().equals(username) && ident.getMotDePasse().equals(password)) {
                returnMsg = "PASS "+password+"<CRLF>----><---- 230 User logged in<CRLF>.";
            }
        } 
        return(returnMsg); 
    }

}
