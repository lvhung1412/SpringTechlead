package com.example.springBootTechlead.security;

public class Endpoints {
    public static final String[] PUBLIC_ENDPOINTS={
            "/user/register",
            "/user/login"
    };

    public static final String[] USER_ENDPOINTS = {
            "/core1/**",
            "/core2/**",
            "/core3/**",
            "/core4/**"
    };

    public static final String[] ADMIN_ENDPOINTS = {
            "/sql1/**",
            "/sql2/**",
            "/sql3/**",
            "/sql4/**"
    };
}

