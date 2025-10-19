package com.meigetsu.arisu.calendar.component;

import org.junit.jupiter.api.Test;

public class FileTest {
    @Test
    public void testReadAllText() throws Exception {
        String path = "./testFile.txt";
        String content = File.readAllText(path);
        String separator = System.getProperty("line.separator");
        String expected = "This is a test file." + separator + "It has multiple lines." + separator + "End of file.";
        assert content.equals(expected) : "File content does not match expected content.";
    }
}
