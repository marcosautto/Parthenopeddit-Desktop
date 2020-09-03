package it.marcosautto.parthenopeddit.api;

import java.net.http.HttpClient;

public class ApiClient {

    private static ApiClient instance;
    private String baseUrl;


    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public ApiClient(){
        instance = this;
    }

    public String getBaseUrl(){ return "http://localhost:8000"; }
    public static ApiClient getInstance() {
        return instance;
    }
    public HttpClient getHttpClient(){ return httpClient; }

    public void performRequest(ApiRoute route){

    }
}
