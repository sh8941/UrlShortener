package com.haider.UrlShortener.Base62;

import org.springframework.stereotype.Service;

@Service
public class Base62Util {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = BASE62.length();

    // Encode a number (ID) to Base62 string
    public static String encode(long value) {
        value += 1000;
        if (value == 0) {
            return String.valueOf(BASE62.charAt(0));
        }

        StringBuilder sb = new StringBuilder();

        while (value > 0) {
            int remainder = (int) (value % BASE);
            sb.append(BASE62.charAt(remainder));
            value /= BASE;
        }

        return sb.reverse().toString();
    }

    // Decode Base62 string back to number (ID)
    public static long decode(String str) {
        long result = 0;

        for (int i = 0; i < str.length(); i++) {
            result = result * BASE + BASE62.indexOf(str.charAt(i));
        }

        return result-1000;
    }
}
