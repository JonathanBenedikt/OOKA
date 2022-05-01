package org.bonn.ooka.LZU;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ThreadManagerTest {

    ThreadManager threadManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    Path path;

    @BeforeEach
    void setUp() {
        // Arrange
        threadManager = new ThreadManager();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        path = Paths.get("./target/codesOOKA-1.0-SNAPSHOT.jar");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void syncLoadedComponent(){
        // Arrange
        Component testComponent = new Component(20,path);
        // Act
        testComponent.load_component();
        threadManager.syncLoadedComponent(testComponent);
        // Assert
        assertEquals("Loaded",threadManager.getStateForComponent(20));
    }

    @Test
    void LoadCommand(){
         // Arrange
        Command load = new LoadCommand(threadManager, path);
        // Act
        load.execute();
        // Assert
        assertEquals("Loaded",threadManager.getStateForComponent(1));
    }

    @Test
    void StartCommand() {
        // Act
        Command load = new LoadCommand(threadManager, path);
        load.execute();
        Command start = new StartCommand(threadManager, 1);
        start.execute();
        // Assert
        assertEquals("Starting",threadManager.getStateForComponent(1));
    }


    @Test
    void stopCommmand(){
        // Arrange
        String expectedOutput = "List of the currently managed Components by the LZU: \n" +
                "ID\t\tState\t\tPath\n";
        // Act & Assert
        Command load = new LoadCommand(threadManager, path);
        load.execute();
        assertEquals("Loaded",threadManager.getStateForComponent(1));

        Command stop = new StopCommand(threadManager, 1);
        stop.execute();

        outContent.reset();
        threadManager.showManagedComponents();
        assertEquals(expectedOutput,outContent.toString());

    }

    @Test
    void showCommand() {
        // Arrange
        Command show = new ShowCommand(threadManager);
        Random rand = new Random();
        int componentCount = rand.nextInt(10);
        String expectedOutput = "List of the currently managed Components by the LZU: \n" +
                "ID\t\tState\t\tPath\n";

        for(int i = 1; i<=componentCount; i++){
            Command load = new LoadCommand(threadManager, path);
            load.execute();
            Command start = new StartCommand(threadManager, i);
            start.execute();

            expectedOutput +=
                    i+"\t\tStarting\t\t"+path+"\n";
        }
        // Act
        outContent.reset();
        show.execute();
        // Assert
        assertEquals(expectedOutput,outContent.toString());
    }


}