package it.marcosautto.parthenopeddit.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class AuthRequests {

    private ApiClient ApiClient;
    private Auth Auth;

    private HttpClient httpClient = ApiClient.getInstance().getHttpClient();

    public int login(String token) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/auth/login"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        return response.statusCode();

    }
}
