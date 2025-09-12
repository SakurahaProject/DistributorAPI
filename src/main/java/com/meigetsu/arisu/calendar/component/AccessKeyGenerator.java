package com.meigetsu.arisu.calendar.component;

import org.springframework.stereotype.Component;

@Component
public final class AccessKeyGenerator extends RandomString {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_LENGTH = 32;

    public AccessKeyGenerator() {
        super(CHARACTERS);
    }

    public String generate() {
        return generate(DEFAULT_LENGTH);
    }
}
