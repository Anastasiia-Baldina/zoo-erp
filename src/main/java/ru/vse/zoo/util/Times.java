package ru.vse.zoo.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public final class Times {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private Times() {
    }

    public static String format(Instant time) {
        if(time == null) {
            return "null";
        }
        return FORMATTER.format(
                time.atZone(TimeZone.getDefault().toZoneId())
        );
    }

    public static Instant parseInstant(String str) {
        if("null".equals(str)) {
            return null;
        }
        return FORMATTER.withZone(ZoneId.systemDefault())
                .parse(str, Instant::from);
    }
}
