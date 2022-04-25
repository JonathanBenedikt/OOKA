package org.bonn.ooka.LZU;

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
        Pattern pattern_stop = Pattern.compile("stop ", Pattern.CASE_INSENSITIVE);
        while(true){
            if (scanner.hasNext()){
                String input = scanner.nextLine();
                Matcher matcher_start = pattern_start.matcher(input);
                Matcher matcher_stop = pattern_stop.matcher(input);
                if (matcher_start.find()){
                    input = input.replace("start ","");
                    Path path = Paths.get(input);
                    Command command = new StartCommand(threadManager, path);
                    command.execute();
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
                    Command command = new HelpCommand();
                    command.execute();
                } else if (input.equals("exit")) {
                    System.out.println("Goodbye ;)");
                    return;
                } else if (input.equals("show")){
                    Command command = new ShowCommand(threadManager);
                    command.execute();
                } else {
                    System.out.println("Invalid input please try again");
                }
            }
        }
    }
}
