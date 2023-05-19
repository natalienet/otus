package ru.nn.service;

import org.junit.jupiter.api.Test;
import ru.nn.api.service.IOService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOStreamsServiceTest {
    @Test
    void testInput() {
        String expected = "123";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(expected.getBytes());
        IOService ioService = new IOStreamsService(null, inputStream);
        String actual = ioService.readString();
        assertEquals(actual, expected);
    }

    @Test
    void testOutput() {
        String expected = "сто двадцать один рубль";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOService ioService = new IOStreamsService(new PrintStream(outputStream),
                new ByteArrayInputStream(new byte[0]));
        ioService.outputString(expected);
        String actual = outputStream.toString();
        assertEquals(expected + System.getProperty("line.separator"), actual);
    }
}
