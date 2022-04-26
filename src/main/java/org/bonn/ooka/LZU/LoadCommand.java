package org.bonn.ooka.LZU;

import java.nio.file.Files;
import java.nio.file.Path;

public class LoadCommand extends Command{

    Path path;
    public LoadCommand(ThreadManager tm, Path path) {
        this.receiver = tm;
        this.path = path;
    }

    private boolean isExecuteable(){
        return Files.exists(this.path);
    }
    @Override
    public void execute() {
        if (isExecuteable()){
            try{
                Component component = new Component(this.receiver.idCounter++, this.path);
                this.receiver.syncLoadedComponent(component);
                component.load_component();
                System.out.println("Component for the jar-path "+path.toString()+" has been loaded.");
            } catch (Exception e) {
                this.receiver.idCounter--;
            }
        } else {
            System.out.println("No valid file has been found.");
        }
    }
}
