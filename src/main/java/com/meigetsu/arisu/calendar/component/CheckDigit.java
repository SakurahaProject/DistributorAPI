package com.meigetsu.arisu.calendar.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckDigit {
    private static List<Integer> reverse(List<Integer> list) {
        Collections.reverse(list);
        return list;
    }

    public static int calcModulus10Weight21(String number) {
        List<Integer> digits = new ArrayList<>();
        for (char c : number.toCharArray())
            digits.add(c - '0');
        digits = reverse(digits);
        int sum = 0;
        for (int i = 0; i < digits.size(); i++) {
            int weight = (i % 2 == 0) ? 2 : 1;
            int product = digits.get(i) * weight;
            sum += (product > 9) ? product - 9 : product;
        }
        int mod = sum % 10;
        return (mod == 0) ? 0 : 10 - mod;
    }
}
