package it.marcosautto.parthenopeddit.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.marcosautto.parthenopeddit.model.Comment;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.util.BuildFormDataFromMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                .uri(URI.create("http://localhost:8000/post/"+postId))
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




}
