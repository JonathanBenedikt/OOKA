package org.bonn.ooka.LZU;

import java.nio.file.Files;
import java.nio.file.Path;

public class StartCommand extends Command{
    private int ComponentID;
    public StartCommand(ThreadManager tm, int id){
        this.receiver = tm;
        this.ComponentID = id;
    }

    @Override
    public void execute() {
        this.receiver.injectLoggerForComponent(this.ComponentID);
        this.receiver.startComponent(this.ComponentID);
    }
}
