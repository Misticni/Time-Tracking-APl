package com.project.demo.security;
public class SecurityConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SECRET = "TimeTracker@AutThennntiT0ken";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String LOGIN_URL = "/rest/login";
    public static final String LOGOUT = "/rest/logout";
    public static final String PUBLIC_URLS="/rest/public/**";
    public final static String CLAIM_AUTHORITY = "authority";




}
