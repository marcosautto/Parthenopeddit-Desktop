package it.marcosautto.parthenopeddit.api;

import it.marcosautto.parthenopeddit.model.Comment;
import it.marcosautto.parthenopeddit.util.BuildFormDataFromMap;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class CommentsRequests {

    private Auth Auth;

    private ApiClient ApiClient;
    private ApiRoute ApiRoute;

    public int publishNewComment(String body, int commented_content_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();
        data.put("body", body);
        data.put("commented_content_id", Integer.toString(commented_content_id));

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/comments/"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        return response.statusCode();

    }
}
