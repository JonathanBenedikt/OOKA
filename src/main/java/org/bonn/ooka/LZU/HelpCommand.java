package org.bonn.ooka.LZU;

import org.bonn.ooka.LZU.Command;

public class HelpCommand extends Command {
    public void execute(){
        System.out.println("RuntimeEnv OOKA Exercise 2\n");
        System.out.println("Commands:");
        System.out.println("help - See this");
        //ID, Status, Verknüpfung, sonstige infos
        System.out.println("show - see description of Components deployed");
        System.out.println("start [path] - launch component specified at given path");
        System.out.println("stop [ID] - stop component");
        System.out.println("exit - stop runtime");
    }
}
