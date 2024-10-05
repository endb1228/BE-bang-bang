package com.bangbang.util.constant;

public class Regex {

    public static final String ACCOUNT = "^[a-z0-9]{1,15}$";
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}\\[\\]:;\"'<>?,./~`-]).{8,}$";
    public static final String NICKNAME = "^[a-zA-Z0-9가-힣]{4,10}$";
}
