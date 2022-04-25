package org.bonn.ooka.LZU;

import java.nio.file.Files;
import java.nio.file.Path;

public class StartCommand extends Command{
    private Path path;
    public StartCommand(ThreadManager tm, Path pathToJar){
        this.receiver = tm;
        this.path = pathToJar;


    }

    private boolean isExecuteable(){
        return Files.exists(this.path);
    }
    @Override
    public void execute() {
        if (isExecuteable()){
            System.out.println("Component for the jar-path "+path.toString()+ "is starting");
            Component componentToStart = new Component(this.receiver.idCounter++, this.path);
            this.receiver.startComponentInThread(componentToStart);
            System.out.println("Component for the jar-path "+path.toString()+" has been created and started.");
        } else {
            System.out.println("No valid file has been found.");
        }
    }
}
