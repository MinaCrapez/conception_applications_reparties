package command;

import tool.Identification;

public class CommandPassword extends Command{
    

    public CommandPassword() {
        super();
    }

    public String run(String password){
        String returnMsg = "error <CRLF>----><----430 wrong password";

        for (Identification ident : Identification.values()) {
            if(ident.getMotDePasse().equals(password)) {
                returnMsg = "PASS "+password+"<CRLF>----><---- 230 User logged in<CRLF>.";
            }
        }
        return(returnMsg); 
    }

}
