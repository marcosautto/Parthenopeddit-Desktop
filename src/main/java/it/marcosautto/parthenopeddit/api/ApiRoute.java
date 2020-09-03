package it.marcosautto.parthenopeddit.api;

import java.util.HashMap;

public abstract class ApiRoute {

    private String url;
    private HashMap<String, String> params;
    private HashMap<String, String> headers;

    public ApiRoute(String url, HashMap<String, String> params, HashMap<String, String> headers){
        this.url = url;
        this.params = params;
        this.headers = headers;
    }


    public int getTimeOut(){ return 3000; }
    public HashMap<String, String> getParamsMap(){
        return params; }
    public HashMap<String, String> getHeadersMap(String token){
        headers.put("Accept", "application/json");
        headers.put("authorization", "Basic "+token);
        return headers;
    }
}
