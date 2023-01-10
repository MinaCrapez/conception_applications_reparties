package command;

public class CommandQUIT extends Command{
    

    public CommandQUIT() {
        super();
    }

    public String run(String message){
        return "<CRLF>----> <---- 221 logout <CRLF>.";
    }

}
