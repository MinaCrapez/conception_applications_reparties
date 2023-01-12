package command;
import tool.Identification;

public class CommandUSER extends Command{
    /**
     * class representing the command username for the FTP Server
     * 10/01/23
     * Mina Crapez - M1 MIAGE
     */

    public CommandUSER() {
        super();
    }

    /**
     * while a username is given, the server will answer
     * @param message the username given by a user
     * @return the answer of the programme with a username given
     * @throws IOException
     */
    public String run(String username){
        String returnMsg = "430 inexisting username<CRLF>.\r\n";

        for (Identification ident : Identification.values()) {
            if (ident.getUsername().equals(username)) {
                System.out.println("user login with username :"+username+". Need password");
                returnMsg = "331 User name ok, need password\r\n";
            }
        }
        return returnMsg;
    }

}
