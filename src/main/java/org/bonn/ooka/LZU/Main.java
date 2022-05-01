package org.bonn.ooka.LZU;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        ThreadManager threadManager = new ThreadManager();

        //File file = new File("file://target/codesOOKA-1.0-SNAPSHOT.jar/target/classes/org/bonn/ooka/cachecomponent/Cache.class");
        //System.out.println(file.exists());

        Scanner scanner = new Scanner(System.in);
        Pattern pattern_start = Pattern.compile("start ", Pattern.CASE_INSENSITIVE);
        Pattern pattern_stop = Pattern.compile("stop ", Pattern.CASE_INSENSITIVE);
        Pattern pattern_load = Pattern.compile("load ", Pattern.CASE_INSENSITIVE);
        while(true){
            if (scanner.hasNext()){
                String input = scanner.nextLine();
                Matcher matcher_start = pattern_start.matcher(input);
                Matcher matcher_stop = pattern_stop.matcher(input);
                Matcher matcher_load = pattern_load.matcher(input);
                if(matcher_load.find()){
                    input = input.replace("load ","");
                    Path path = Paths.get(input);
                    Command load = new LoadCommand(threadManager, path);
                    load.execute();
                }
                else if (matcher_start.find()){
                    input = input.replace("start ","");
                    int id;
                    try {
                        id = Integer.parseInt(input);
                        Command start = new StartCommand(threadManager, id);
                        start.execute();
                    }
                    catch (NumberFormatException e) {
                        System.out.println("No Component under the ID: " + input);
                    };
                } else if(matcher_stop.find()) {
                    input = input.replace("stop ","");
                    int id;
                    try {
                        id = Integer.parseInt(input);
                        Command stop = new StopCommand(threadManager, id);
                        stop.execute();
                    }
                    catch (NumberFormatException e) {
                        System.out.println("No Component under the ID: " + input);
                    }
                }
                else if (input.equals("help")){
                    Command help = new HelpCommand();
                    help.execute();
                } else if (input.equals("exit")) {
                    System.out.println("Goodbye ;)");
                    return;
                } else if (input.equals("show")){
                    Command show = new ShowCommand(threadManager);
                    show.execute();
                } else {
                    System.out.println("Invalid input please try again");
                }
            }
        }
    }
}
