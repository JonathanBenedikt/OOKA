package org.bonn.ooka.LZU;

public class DeleteCommand extends Command {
    private int ComponentID;
    public DeleteCommand(ThreadManager tm, int ID){
        this.receiver = tm;
        this.ComponentID = ID;
    }

    @Override
    public void execute(){
        this.receiver.deleteComponent(ComponentID);
    }
}
