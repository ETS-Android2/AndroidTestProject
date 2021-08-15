package com.datechnologies.androidtest.login;

public class LoginResponse
{
    private String code;
    private String message;

    public LoginResponse(String code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
