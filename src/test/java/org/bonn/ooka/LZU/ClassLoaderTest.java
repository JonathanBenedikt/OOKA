package org.bonn.ooka.LZU;

import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.jar.JarFile;

import static org.junit.jupiter.api.Assertions.*;

class ClassLoaderTest {

    CustomClassLoader classLoader;
    LinkedList<Class> classList;

    @BeforeEach
    void setUp() {
        // Arrange
       // classLoader = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        classList = new LinkedList<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadJar() {
        // Act
        try {
            CustomClassLoader firstLoader = new CustomClassLoader(new URL[]{new URL("file:///InsertPathToJar")}, null);
            classList = firstLoader.loadJar();
        }catch (Exception ex)
        {
            ex.printStackTrace();
            fail();
        }
        // Assert
        assertTrue(classList.size() > 0);
    }

    @Test
    void testIsolation(){
        try {
            CustomClassLoader firstLoader = new CustomClassLoader(new URL[]{new URL("file:///InsertPathToJar")},null);
            CustomClassLoader secondLoader = new CustomClassLoader(new URL[]{new URL("file:///InsertPathToJar")},null);


            LinkedList<Class> firstClassList = firstLoader.loadJar();
            LinkedList<Class> referenceToFirstClassList = firstLoader.loadJar();
            LinkedList<Class> secondClassList = secondLoader.loadJar();

            assert(firstClassList.getFirst() != secondClassList.getFirst());
            assert(firstClassList.getFirst() == referenceToFirstClassList.getFirst());


        }catch (Exception ex)
        {
            ex.printStackTrace();
            fail();
        }


    }



    @Test
    void classLoaderExceptions(){
        assertThrows(Exception.class, () -> classLoader.loadClass("Snake Oil"), "Expected loadClass to throw an Exception, but it didn't");
    }
}