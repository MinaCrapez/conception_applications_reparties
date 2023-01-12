package command;

import java.io.OutputStream;

public abstract class Command {
    /**
     * abstract class representing a command for the FTP Server
     * 10/01/23
     * Mina Crapez - M1 MIAGE
     */

    protected OutputStream output;

    public Command() {
    }

    abstract public String run(String commandAsk); 

    public OutputStream getOutput () {
        return this.output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }



    
}
