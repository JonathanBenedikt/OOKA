package org.bonn.ooka.LZU;

public class RestoreCommand extends Command{

    public RestoreCommand(ThreadManager tm){
        this.receiver = tm;
    }

    public void execute(){
        receiver.restoreStatesFromFile();
    }
}
