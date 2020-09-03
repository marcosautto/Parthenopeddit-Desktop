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

    /**
     *  - publishNewReview -
     *  Pubblica una recensione al corso di cui è passato l'ID
     */
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

    /**
     *  - getReview -
     *  Ottieni la recensione di cui si passa l'ID
     */
    public Review getReview(int reviewId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/reviews/"+reviewId))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Review review = new Gson().fromJson(response.body(), Review.class);

        return review;
    }

    /**
     *  - getReviewWithComments -
     *  Ottieni la recensione con i commenti di cui si passa l'ID
     */
    public Review getReviewWithComments(int reviewId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ApiClient.getInstance().getBaseUrl()+"/reviews/"+reviewId+"/comments"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Review review = new Gson().fromJson(response.body(), Review.class);

        return review;
    }

    /**
     *  - likeReview -
     *  Invia un upvote alla recensione dato il suo ID
     */
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

    /**
     *  - dislikeReview -
     *  Invia un downvote alla recensione dato il suo ID
     */
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
