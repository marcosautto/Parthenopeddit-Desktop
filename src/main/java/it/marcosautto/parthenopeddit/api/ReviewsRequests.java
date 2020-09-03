package it.marcosautto.parthenopeddit.api;

import com.google.gson.Gson;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.model.Review;
import it.marcosautto.parthenopeddit.util.BuildFormDataFromMap;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ReviewsRequests {

    private Auth Auth;
    private ApiClient ApiClient;
    private ApiRoute ApiRoute;


    public int publishNewReview(String body, int reviewed_course_id, int score_liking, int score_difficulty) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();
        data.put("body", body);
        data.put("reviewed_course_id", Integer.toString(reviewed_course_id));
        data.put("score_liking", Integer.toString(score_liking));
        data.put("score_difficulty", Integer.toString(score_difficulty));

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/reviews/"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        return response.statusCode();
    }
    
    public Review getReview(int reviewId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/reviews/"+reviewId))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());

        Review review = new Gson().fromJson(response.body(), Review.class);

        return review;
    }
    
    public Review getReviewWithComments(int reviewId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/reviews/"+reviewId+"/comments"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());
        System.out.println("Lol0");

        Review review = new Gson().fromJson(response.body(), Review.class);

        System.out.println("Lol1");

        //Devo convertire la lista di commenti
        //Type listType = new TypeToken<List<Comment>>(){}.getType();
        //List<Comment> list = new ArrayList<Comment>();
        //list = gson.fromJson(jsonOutput, listType);
        //ObservableList<Comment> comments = FXCollections.observableList(list);
        System.out.println("Lol2");
        //post.setComments(comments);


        return review;
    }

    public int likeReview(int review_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/reviews/"+review_id+"/like"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();

    }

    public int dislikeReview(int review_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/reviews/"+review_id+"/dislike"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        return response.statusCode();

    }
}
