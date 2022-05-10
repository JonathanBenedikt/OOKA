package org.bonn.ooka.LZU;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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


    public void loggerInjector(Component c){
        Field loggerField = c.getAnnotatedField(Inject.class);

        if(loggerField == null )
            return;

        if(loggerField.getType().getClass().equals(Logger.class.getClass()) ){
            try {
                loggerField.setAccessible(true);
                loggerField.set(this, new Logger());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @BeforeEach
    void setUp() {
    // Arrange
        rand = new Random();
        id = rand.nextInt();
      //path = Paths.get("Please insert full path to a .jar-file.");
        path = Paths.get("/home/jonathan/IdeaProjects/codesOOKA/target/codesOOKA-1.0-SNAPSHOT.jar");
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
        assertEquals( "Initializing", testComponent.getState());
    }

    @Test
    void loadComponent(){
        // Act
        testComponent.load_component();
        loggerInjector(testComponent);
        // Assert
        assertEquals("Loaded",testComponent.getState());
    }

    @Test
    void runComponent(){
        // Act
        testComponent.load_component();
        loggerInjector(testComponent);
        testComponent.run();
        // Assert
        assertEquals("Running",testComponent.getState());
    }

    @Test
    void stopComponent(){
        // Act
        testComponent.load_component();
        loggerInjector(testComponent);
        testComponent.run();
        testComponent.stopComponent();
        // Assert
        assertEquals("Stopped",testComponent.getState());
    }


}