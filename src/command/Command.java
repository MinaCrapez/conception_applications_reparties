package command;

abstract public class Command {
    /**
     * abstract classe representing a command for the FTP Server
     * 10/01/23
     * Mina Crapez - M1 MIAGE
     */

    public Command() {
    }

    abstract public String run(String commandAsk); 


    
}
