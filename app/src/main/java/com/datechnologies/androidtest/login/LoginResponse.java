package com.datechnologies.androidtest.login;

/** Retrofit response model which is used to store data obtain from the REST web client GET call.
 */
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
