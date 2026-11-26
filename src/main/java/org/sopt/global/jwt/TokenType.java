package org.sopt.global.jwt;

import java.util.Arrays;
import java.util.Optional;

public enum TokenType {
    ACCESS,
    REFRESH;

    public static final String CLAIM_NAME = "type";

    public static Optional<TokenType> from(String value) {
        return Arrays.stream(values())
                .filter(type -> type.name().equals(value))
                .findFirst();
    }
}
