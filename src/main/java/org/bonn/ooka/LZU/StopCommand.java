package org.bonn.ooka.LZU;

public class StopCommand extends Command{
    private int ComponentID;
    public StopCommand(ThreadManager tm, int ID){
        this.receiver = tm;
        this.ComponentID = ID;
    }

    @Override
    public void execute() {
        this.receiver.stopComponent(this.ComponentID);
        this.receiver.saveStatesToFile();
    }
}
