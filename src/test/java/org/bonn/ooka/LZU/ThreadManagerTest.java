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

import static org.junit.jupiter.api.Assertions.*;

class ThreadManagerTest {

    ThreadManager threadManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        threadManager = new ThreadManager();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void startComponentInThread() {
        // Arrange
        Component testComponent = new Component(1, Paths.get("./target/codesOOKA-1.0-SNAPSHOT.jar"));
        // Act
        //threadManager.startComponentInThread(testComponent);
        threadManager.startComponent(1);
        // Assert

        // assertEquals("Running",testComponent.getState());

    }

    @Test
    void showManagedComponents() {
        // Arrange
        Path examplePath = Paths.get("./target/codesOOKA-1.0-SNAPSHOT.jar");
        Random rand = new Random();
        int componentCount = rand.nextInt(10);
        String expectedOutput = "List of the currently managed Components by the LZU: \n" +
                "ID\t\tState\t\tPath\n";

        for(int i = 0; i<componentCount; i++){
            Component testComponent = new Component(i, examplePath );
            threadManager.startComponent(i);
            expectedOutput +=
                    i+"\t\tStarting\t\t"+examplePath+"\n";
        }
        // Act
        outContent.reset();
        threadManager.showManagedComponents();
        // Assert
        assertEquals(outContent.toString(), expectedOutput);
    }


}