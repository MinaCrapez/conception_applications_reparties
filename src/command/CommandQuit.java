package command;

public class CommandQuit extends Command{
    

    public CommandQuit() {
        super();
    }

    public String run(String message){
        return "<CRLF>----> <---- 221 logout <CRLF>.";
    }

}
