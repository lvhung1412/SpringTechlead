package com.example.springBootTechlead.security;

public class Endpoints {
    public static final String[] PUBLIC_POST_ENDPOINTS={
            "/user/register",
            "/user/login"
    };

    public static final String[] USER_GET_ENDPOINTS = {
            "/core1/level1/ex1",
            "/core1/level1/ex2",
            "/core1/level1/ex3"
    };
    public static final String[] USER_POST_ENDPOINTS = {
            "/core2/**",
            "/core3/**",
            "/core4/**",
            "/core1/level2/**",
            "/core1/level3/**",
            "/core1/level4/**",
            "/core1/level1/ex4",
            "/core1/level1/ex5",
            "/core1/level1/ex6",
            "/core1/level1/ex7",
            "/core1/level1/ex8",
            "/core1/level1/ex9",
            "/core1/level1/ex10"
    };

    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/sql1/**",
            "/sql2/**",
            "/sql3/**"
    };
    public static final String[] ADMIN_PUT_ENDPOINTS = {
            "/sql4/**"
    };
}

