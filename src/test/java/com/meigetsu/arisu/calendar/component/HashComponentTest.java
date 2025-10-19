package com.meigetsu.arisu.calendar.component;

import java.util.HexFormat;

import org.junit.jupiter.api.Test;

public class HashComponentTest {
    private static final byte[] text = "DCAQSpov49L0HWw9yfkPaFgrU3RVyo".getBytes();

    @Test
    public void testMD5() {
        HashComponent component = new HashComponent("MD5");
        byte[] byteResult = component.toHash(text);
        String strResult = HexFormat.of().formatHex(byteResult).toUpperCase();
        assert strResult.equals("0B8D48D87FB3DE6B69DB6890D039F3F8");
    }

    @Test
    public void testSHA1() {
        HashComponent component = new HashComponent("SHA-1");
        byte[] byteResult = component.toHash(text);
        String strResult = HexFormat.of().formatHex(byteResult).toUpperCase();
        assert strResult.equals("FF537F60EF1445C892FABEB0F82A315C488D6CF0");
    }

    @Test
    public void testSHA256() {
        HashComponent component = new HashComponent("SHA-256");
        byte[] byteResult = component.toHash(text);
        String strResult = HexFormat.of().formatHex(byteResult).toUpperCase();
        assert strResult.equals("0CA59417A3EFED0F6559ED4221A0CEBD9E6601CD3E334EBF8875B0F850154DF7");
    }

    @Test
    public void testSHA512() {
        HashComponent component = new HashComponent("SHA-512");
        byte[] byteResult = component.toHash(text);
        String strResult = HexFormat.of().formatHex(byteResult).toUpperCase();
        assert strResult.equals(
                "6E5C38B1765B25E54A238B2543ACB95C3FBE422AB62EA69A1C8AA2F246C42EE1AFCFB1B4F6AC6C66AB1B8F87658728859489FDED75112788EA159C0E13D5A0A8");
    }
}
