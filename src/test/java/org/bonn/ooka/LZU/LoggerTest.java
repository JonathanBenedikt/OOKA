package org.bonn.ooka.LZU;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    private Logger logger;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp()
    {
        // Arrange
        StandardLoggerFactory factory = (StandardLoggerFactory) FactoryProvider.getFactory("StandardLoggerFactory");
        logger = factory.createLogger();
    }

    @Test
    void sendLog() {
        // Arrange
        outContent.reset();
        String expectedLog = "+++++ LOG: "+"Dies ist ein Testlog";
        // Act
        logger.sendLog("Dies ist ein Testlog");
        // Assert
        System.out.println(outContent.toString());
        System.out.println(expectedLog.toString());
    }


}