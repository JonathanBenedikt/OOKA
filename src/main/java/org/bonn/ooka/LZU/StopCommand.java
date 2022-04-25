package org.bonn.ooka.LZU;

public class StopCommand extends Command{


    public StopCommand(ThreadManager tm, int ID){
        this.receiver = tm;
    }

    @Override
    public void execute() {

    }
}
