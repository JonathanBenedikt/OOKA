package org.bonn.ooka.LZU;

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
    Path path;


    @BeforeEach
    void setUp() {
    // Arrange
        rand = new Random();
      id = rand.nextInt();
      path = Paths.get("./target/codesOOKA-1.0-SNAPSHOT.jar");
      testComponent = new Component(id,path);
    }

    @Test
    void getID(){
        // Assert
        assertEquals(id, testComponent.getID());
    }

    @Test
    void getPath(){
        // Assert
        assertEquals(path.toString(), testComponent.getPath());
    }

    @Test
    void getState(){
        // Assert
        assertEquals( "Initializing", testComponent.getState());
    }

    @Test
    void loadComponent(){
        // Act
        testComponent.load_component();
        // Assert
        assertEquals("Loaded",testComponent.getState());
    }

    @Test
    void runComponent(){
        // Act
        testComponent.load_component();
        testComponent.run();
        // Assert
        assertEquals("Running",testComponent.getState());
    }

    @Test
    void stopComponent(){
        // Act
        testComponent.load_component();
        testComponent.run();
        testComponent.stopComponent();
        // Assert
        assertEquals("Stopped",testComponent.getState());
    }


}