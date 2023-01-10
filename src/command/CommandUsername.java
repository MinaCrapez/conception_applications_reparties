package command;
import tool.Identification;

public class CommandUsername extends Command{
    /**
     * class representing the command username for the FTP Server
     * 10/01/23
     * Mina Crapez - M1 MIAGE
     */

    public CommandUsername() {
        super();
    }

    /**
     * while a username is given, the server will answer
     * @param message the username given by a user
     * @return the answer of the programme with a username given
     * @throws IOException
     */
    public String run(String username){
        String returnMsg = "error <CRLF>----><----430 inexisting username<CRLF>.";

        for (Identification ident : Identification.values()) {
            if (ident.getUsername().equals(username)) {
                returnMsg = "USER "+username+" <CRLF>----> <---- 331 User name ok, need password<CRLF>.";
            }
        }
        return returnMsg;
    }

}
