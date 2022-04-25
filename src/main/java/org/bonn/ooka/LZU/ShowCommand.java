package org.bonn.ooka.LZU;

public class ShowCommand extends Command {
    public ShowCommand(ThreadManager tm){
        this.receiver = tm;
    }
    public void execute(){
        this.receiver.showManagedComponents();
    }
}
