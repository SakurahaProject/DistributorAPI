package com.meigetsu.arisu.calendar.component;

import org.junit.jupiter.api.Test;

public class AccessKeyGeneratorTest {
    @Test
    public void testGenerate() {
        AccessKeyGenerator generator = new AccessKeyGenerator();
        String key = generator.generate();
        assert key.length() == 32;
        assert key.matches("^[A-Za-z0-9]{32}$");
    }
}
