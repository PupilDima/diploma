package com.kovalenko.diploma.utils;

public final class QueryHelper {

    private static final String LIKE = "%";

    private QueryHelper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String likePattern(String value) {
        return LIKE + value + LIKE;
    }

}
