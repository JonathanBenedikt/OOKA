package org.bonn.ooka.LZU;

import org.bonn.ooka.LZU.Component;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ComponentTest {

    Component testComponent;
    Random rand;
    int id;

    @Test
    void run() {
    }

    @BeforeEach
    void setUp() {
      rand = new Random();
      id = rand.nextInt();
      Path jar_path = Paths.get("./target/codesOOKA-1.0-SNAPSHOT.jar");
      testComponent = new Component(id,jar_path);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getID() {
       assertTrue(testComponent.getID() == this.id);
    }

    @Test
    void getState() {
        assertTrue(testComponent.getState() == "Running");
    }

    @Test
    void isRunning() {
        assertTrue(testComponent.isRunning() == true);
    }

    @Test
    void getPath() {
        assertTrue(testComponent.getPath() == "./target/codesOOKA-1.0-SNAPSHOT.jar");
    }

    @Test
    void testRun() {

    }

    @Test
    void kill() {
        //testComponent.kill();
        assertTrue(testComponent.isRunning() == false);
    }
}