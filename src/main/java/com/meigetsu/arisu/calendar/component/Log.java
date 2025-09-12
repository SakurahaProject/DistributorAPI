package com.meigetsu.arisu.calendar.component;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Log {
    public static void access(String id, String endpoint, String logText) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("access.log", true))) {
            bw.write(String.format("%s\t%s\t%s\n", id, endpoint, logText));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void error(String id, String endpoint, String logText) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("error.log", true))) {
            bw.write(String.format("%s\t%s\t%s\n", id, endpoint, logText));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
