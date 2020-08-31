package it.marcosautto.parthenopeddit.api;

import com.google.gson.Gson;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.util.BuildFormDataFromMap;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class PostsRequests {

    private Auth auth;

    private ApiClient ApiClient;
    private ApiRoute ApiRoute;

    public PostsRequests(Auth auth){
        //this.auth = auth;
    }

    public int publishNewPost(String title, String body, int board_id) throws IOException, InterruptedException {

        Map<Object, Object> data = new HashMap<>();
        data.put("title", title);
        data.put("body", body);
        data.put("board_id", Integer.toString(0));

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/posts/"))
                .setHeader("authorization", auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        return response.statusCode();
    }


    public Post getPost(int postId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/posts/"+postId))
                .setHeader("authorization", auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());

        Post post = new Gson().fromJson(response.body(), Post.class);

        return post;
    }

    public Post getPostWithComments(int postId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/posts/"+postId+"/comments"))
                .setHeader("authorization", auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());
        System.out.println("Lol0");

        Post post = new Gson().fromJson(response.body(), Post.class);
        System.out.println("Lol1");

        //Devo convertire la lista di commenti
        //Type listType = new TypeToken<List<Comment>>(){}.getType();
        //List<Comment> list = new ArrayList<Comment>();
        //list = gson.fromJson(jsonOutput, listType);
        //ObservableList<Comment> comments = FXCollections.observableList(list);
        System.out.println("Lol2");
        //post.setComments(comments);


        return post;
    }

    public int likePost(int post_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/posts/"+post_id+"/like"))
                .setHeader("authorization", auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();

    }

    public int dislikePost(int post_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/posts/"+post_id+"/dislike"))
                .setHeader("authorization", auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        return response.statusCode();

    }




}
