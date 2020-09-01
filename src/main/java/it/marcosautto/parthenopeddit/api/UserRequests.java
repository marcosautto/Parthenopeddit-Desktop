package it.marcosautto.parthenopeddit.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.marcosautto.parthenopeddit.model.*;
import it.marcosautto.parthenopeddit.util.BuildFormDataFromMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class UserRequests {
    private Auth Auth;

    private ApiClient ApiClient;

    public UserRequests(){
        //this.Auth = Auth;
    }

    public ObservableList<User> searchUser(String searched_user_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/user/search/"+searched_user_id))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();
        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        String jsonOutput = response.body();

        Type listType = new TypeToken<List<User>>(){}.getType();
        List<User> list = new ArrayList<User>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<User> users = FXCollections.observableList(list);
        return users;
    }

    public User getUserByID(String fetched_user_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/user/"+fetched_user_id))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();
        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        User user = new Gson().fromJson(response.body(), User.class);

        return user;
    }
    
    public int setDisplayName(String name) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();
        data.put("display_name", name);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/user/display_name"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public ObservableList<Post> getUserFeed(int page, int perPage, String transactionStartDataTime) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/user/feed/"+perPage+"/"+page))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        String jsonOutput = response.body();
        //System.out.println(response.body());
        Type listType = new TypeToken<List<Post>>(){}.getType();
        List<Post> list = new ArrayList<Post>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<Post> posts = FXCollections.observableList(list);

        return posts;
    }

    public ObservableList<Comment> getUserPublishedComments(String user_id, int page, int perPage, String transactionStartDateTime) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/user/"+user_id+"/published_comments/"+perPage+"/"+page))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        //System.out.println(response.body());
        Type listType = new TypeToken<List<Comment>>(){}.getType();
        List<Comment> list = new ArrayList<Comment>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<Comment> comments = FXCollections.observableList(list);

        return comments;
    }

    public ObservableList<Post> getUserPublishedPosts(String user_id, int page, int perPage, String transactionStartDateTime) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/user/"+user_id+"/published_posts/"+perPage+"/"+page))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        //System.out.println(response.body());
        Type listType = new TypeToken<List<Post>>(){}.getType();
        List<Post> list = new ArrayList<Post>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<Post> posts = FXCollections.observableList(list);

        return posts;
    }

    public ObservableList<Review> getUserPublishedReviews(String user_id, int page, int perPage, String transactionStartDateTime) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/user/"+user_id+"/published_reviews/"+perPage+"/"+page))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        //System.out.println(response.body());
        Type listType = new TypeToken<List<Review>>(){}.getType();
        List<Review> list = new ArrayList<Review>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<Review> reviews = FXCollections.observableList(list);

        return reviews;
    }
}
