package org.bonn.ooka.LZU;

import javax.print.DocFlavor;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        ThreadManager threadManager = new ThreadManager();

        Scanner scanner = new Scanner(System.in);
        Pattern pattern_start = Pattern.compile("start ", Pattern.CASE_INSENSITIVE);
        while(true){
            if (scanner.hasNext()){
                String input = scanner.nextLine();
                Matcher matcher_start = pattern_start.matcher(input);
                if (matcher_start.find()){
                    input = input.replace("start ","");
                    Path path = Paths.get(input);

                    if(Files.exists(path)){
                        // Try to start the found file
                        threadManager.startThread(path);

                    }else {
                        System.out.println("No valid file has been found.");
                    }
                }
                else if (input.equals("help")){
                    System.out.println("RuntimeEnv OOKA Exercise 2\n");
                    System.out.println("Commands:");
                    System.out.println("help - See this");
                    //ID, Status, Verknüpfung, sonstige infos
                    System.out.println("show - see description of Components deployed");
                    System.out.println("start [path] - launch component specified at given path");
                    System.out.println("stop [ID] - stop component");
                    System.out.println("exit - stop runtime");
                } else if (input.equals("exit")) {
                    System.out.println("Goodbye ;)");
                    return;
                }
                else {
                    System.out.println("Invalid input please try again");
                }
            }
        }
    }
}
