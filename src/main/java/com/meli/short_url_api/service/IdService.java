package com.meli.short_url_api.service;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;


public class IdService {
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = BASE62.length();

    public static String generateShortID() {
        int number = new Random().nextInt(1000000);// Simula un ID numerico
        StringBuilder shortID = new StringBuilder();
        while (number > 0) {
            shortID.append(BASE62.charAt(number % BASE));
            number /= BASE;
        }
        return shortID.reverse().toString();
    }

    public static String encodeBase62(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(BASE62.charAt((int) (value % BASE62.length())));
            value /= BASE62.length();
        }
        return sb.reverse().toString();
    }

    public static String generateShortUrlId(String longUrl) {
        // Generar hash (MurmurHash produce un número de 32 bits)
        long hash = Hashing.murmur3_32()
                .hashString(longUrl, StandardCharsets.UTF_8)
                .asInt();

        // Convertir hash a Base62
        return encodeBase62(hash & 0xFFFFFFFFL); // Convertimos a positivo
    }
}
