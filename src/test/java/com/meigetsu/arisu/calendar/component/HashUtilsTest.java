package com.meigetsu.arisu.calendar.component;

import org.junit.jupiter.api.Test;

public class HashUtilsTest {
    private static final String text = "DCAQSpov49L0HWw9yfkPaFgrU3RVyo";

    @Test
    public void testToHashHexSingleStretch() {
        String md5Hex = HashUtils.toHash(text, "md5", "hex");
        assert md5Hex.equals("0B8D48D87FB3DE6B69DB6890D039F3F8");
        String sha1Hex = HashUtils.toHash(text, "sha1", "hex");
        assert sha1Hex.equals("FF537F60EF1445C892FABEB0F82A315C488D6CF0");
        String sha256Hex = HashUtils.toHash(text, "sha256", "hex");
        assert sha256Hex.equals("0CA59417A3EFED0F6559ED4221A0CEBD9E6601CD3E334EBF8875B0F850154DF7");
        String sha512Hex = HashUtils.toHash(text, "sha512", "hex");
        assert sha512Hex.equals(
                "6E5C38B1765B25E54A238B2543ACB95C3FBE422AB62EA69A1C8AA2F246C42EE1AFCFB1B4F6AC6C66AB1B8F87658728859489FDED75112788EA159C0E13D5A0A8");
    }

    @Test
    public void testToHashBase64SingleStretch() {
        String md5Base64 = HashUtils.toHash(text, "md5", "base64");
        assert md5Base64.equals("C41I2H+z3mtp22iQ0Dnz+A==");
        String sha1Base64 = HashUtils.toHash(text, "sha1", "base64");
        assert sha1Base64.equals("/1N/YO8URciS+r6w+CoxXEiNbPA=");
        String sha256Base64 = HashUtils.toHash(text, "sha256", "base64");
        assert sha256Base64.equals("DKWUF6Pv7Q9lWe1CIaDOvZ5mAc0+M06/iHWw+FAVTfc=");
        String sha512Base64 = HashUtils.toHash(text, "sha512", "base64");
        assert sha512Base64.equals(
                "blw4sXZbJeVKI4slQ6y5XD++Qiq2LqaaHIqi8kbELuGvz7G09qxsZqsbj4dlhyiFlIn97XURJ4jqFZwOE9WgqA==");
    }

    @Test
    public void testToHashHexMultiStretch() {
        final int stretchCount = 5;
        String md5Hex = HashUtils.toHash(text, "md5", "hex", stretchCount);
        assert md5Hex.equals("8152FBC713A281E2EEF6D9DB4E5CBA51");
        String sha1Hex = HashUtils.toHash(text, "sha1", "hex", stretchCount);
        assert sha1Hex.equals("624604EB161A6F96D125112F0DD00A96E31148AA");
        String sha256Hex = HashUtils.toHash(text, "sha256", "hex", stretchCount);
        assert sha256Hex.equals("CA5CFB3FAF7D26B5C61E3EDD47A5F8AC61AF28E70F12DAB80F394B1A7D93DE8B");
        String sha512Hex = HashUtils.toHash(text, "sha512", "hex", stretchCount);
        assert sha512Hex.equals(
                "D251A702CB63603BC3CAB231B50CD7284D6CCC56391175C84D1A7A114B7CE1447E7FD89B8661AA8CFCD940FE609D2067588B633ABB3873B4E43C423C64AF1464");
    }

    @Test
    public void testToHashBase64MultiStretch() {
        final int stretchCount = 5;
        String md5Base64 = HashUtils.toHash(text, "md5", "base64", stretchCount);
        assert md5Base64.equals("gVL7xxOigeLu9tnbTly6UQ==");
        String sha1Base64 = HashUtils.toHash(text, "sha1", "base64", stretchCount);
        assert sha1Base64.equals("YkYE6xYab5bRJREvDdAKluMRSKo=");
        String sha256Base64 = HashUtils.toHash(text, "sha256", "base64", stretchCount);
        assert sha256Base64.equals("ylz7P699JrXGHj7dR6X4rGGvKOcPEtq4DzlLGn2T3os=");
        String sha512Base64 = HashUtils.toHash(text, "sha512", "base64", stretchCount);
        assert sha512Base64.equals(
                "0lGnAstjYDvDyrIxtQzXKE1szFY5EXXITRp6EUt84UR+f9ibhmGqjPzZQP5gnSBnWItjOrs4c7TkPEI8ZK8UZA==");
    }

    @Test
    public void testPassword() {
        String passwordHash = HashUtils.Password(text);
        assert passwordHash.equals(
                "36F84346A1F4762B524C002393935D3874D9D191E5F990B0A0D3787BDE23F1409B8E9A540B5586D6E42051DA400A0E85C431098D24136784D14BD71854BD4506");
    }

    @Test
    public void testAccessKey() {
        String accessKeyHash = HashUtils.AccessKey(text);
        assert accessKeyHash.equals("B54D5F9F55703B0F808D98B1B5B87DABEE59F5BE83A6365F9D86C089A82839D3");
    }
}
