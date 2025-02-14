package ru.vse.zoo.util;

import java.util.Collection;

public final class Ensure {

    private Ensure() {
    }

    public static <T> T notNull(T param, String paramName) {
        if (param == null) {
            throw new IllegalArgumentException("Parameter [" + paramName + "] must not be null");
        }
        return param;
    }

    public static <T, C extends Collection<T>> C notEmpty(C param, String paramName) {
        if (notNull(param, paramName).isEmpty()) {
            throw new IllegalArgumentException("Parameter [" + paramName + "] must not be empty");
        }
        return param;
    }
}
