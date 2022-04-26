package org.bonn.ooka.LZU;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CustomClassLoaderTest {

    CustomClassLoader classLoader;
    LinkedList<Class> classList;

    @BeforeEach
    void setUp() {
        classLoader = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        classList = new LinkedList<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadClass() {
        classList = classLoader.loadJar("/Users/niclaswolf/SynologyDrive/Studium/Master/Objektorientierte Komponenten-Architekturen/Repository/target/codesOOKA-1.0-SNAPSHOT.jar");

    }

    @Test
    void loadJar() {
        // Act
        classList = classLoader.loadJar("/Users/niclaswolf/SynologyDrive/Studium/Master/Objektorientierte Komponenten-Architekturen/Repository/target/codesOOKA-1.0-SNAPSHOT.jar");
        // Assert
        assertTrue(classList.size() > 0);
    }
}