package com.noman.security.contants;

import io.jsonwebtoken.Jwts;

public final class ApplicationConstants {
    public static final String JWT_SECRET = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = String.valueOf(Jwts.SIG.HS256.key().build());
}
