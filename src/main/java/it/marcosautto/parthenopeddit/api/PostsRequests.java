package it.marcosautto.parthenopeddit.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.marcosautto.parthenopeddit.model.Course;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.util.BuildFormDataFromMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostsRequests {

    private Auth auth;

    private ApiClient ApiClient;
    private ApiRoute ApiRoute;

    public PostsRequests(){
        //this.auth = auth;
    }

    /**
     *  - searchPost -
     *  Ricerca la lista dei post il cui titolo
     *  contiene la stringa inviata
     */
    public ObservableList<Post> searchPost(String searched_post_title) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/posts/search/"+searched_post_title))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();

        Type listType = new TypeToken<List<Post>>(){}.getType();
        List<Post> list = new ArrayList<Post>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<Post> posts = FXCollections.observableList(list);

        return posts;
    }

    /**
     *  - publishNewPost -
     *  Pubblica un post nella board di cui viene passato l'ID
     */
    public int publishNewPost(String title, String body, int board_id) throws IOException, InterruptedException {

        Map<Object, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("body", body);
        data.put("board_id", Integer.toString(board_id));

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/posts/"))
                .setHeader("authorization", auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    /**
     *  - getPost -
     *  Ottieni un post dato il suo ID
     */
    public Post getPost(int postId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/posts/"+postId))
                .setHeader("authorization", auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();

        Post post = new Gson().fromJson(response.body(), Post.class);

        return post;
    }

    /**
     *  - getPostWithComments -
     *  Ottieni un post con i suoi commenti dato il suo ID
     */
    public Post getPostWithComments(int postId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/posts/"+postId+"/comments"))
                .setHeader("authorization", auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();

        Post post = new Gson().fromJson(response.body(), Post.class);

        return post;
    }

    /**
     *  - likePost -
     *  Invia un upvote al post dato il suo ID
     */
    public int likePost(int post_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/posts/"+post_id+"/like"))
                .setHeader("authorization", auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    /**
     *  - dislikePost -
     *  Invia un downvote al post dato il suo ID
     */
    public int dislikePost(int post_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/posts/"+post_id+"/dislike"))
                .setHeader("authorization", auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        return response.statusCode();
    }




}
