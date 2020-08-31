package it.marcosautto.parthenopeddit.api;

import java.util.Base64;

public class Auth {
    private String token;
    private String username;
    private String password;

    private static Auth instance;

    public Auth(String username, String password){
        instance = this;
        this.username = username;
        this.password = password;
        token = Base64.getEncoder().encodeToString((username+":"+password).getBytes());
        token = "Basic "+token;
    }

    public String getToken(){ return token; }
    public static Auth getInstance() {
        return instance;
    }

}
