package it.marcosautto.parthenopeddit.api;

import com.google.gson.Gson;
import it.marcosautto.parthenopeddit.model.Comment;
import it.marcosautto.parthenopeddit.model.Post;
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

    /**
     *  - publishNewComment -
     *  Pubblica un nuovo commento passando il contenuto del commento
     *  e l'ID del contenuto commentato
     */
    public int publishNewComment(String body, int commented_content_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();
        data.put("body", body);
        data.put("commented_content_id", Integer.toString(commented_content_id));

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/comments/"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    /**
     *  - getContent -
     *  Ottieni un commento dato il suo ID
     */
    public Comment getComment(int commentId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/comments/"+commentId))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());
        Comment comment = new Gson().fromJson(response.body(), Comment.class);

        return comment;
    }

    /**
     *  - likeComment -
     *  Invia un upvote al commento dato il suo ID
     */
    public int likeComment(int comment_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/comments/"+comment_id+"/like"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    /**
     *  - dislikeComment -
     *  Invia un downvote al commento dato il suo ID
     */
    public int dislikeComment(int comment_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/comments/"+comment_id+"/dislike"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }
}
