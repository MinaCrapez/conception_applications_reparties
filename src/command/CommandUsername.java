package command;
import tool.Identification;

public class CommandUsername extends Command{
    

    public CommandUsername() {
        super();
    }

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
