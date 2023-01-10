package command;
abstract public class Command {

    protected String commandAsk;


    public Command() {
        //this.commandAsk = commandAsk;
    }

    abstract public String run(String commandAsk);


    
}
