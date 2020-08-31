package it.marcosautto.parthenopeddit.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.marcosautto.parthenopeddit.model.Course;
import it.marcosautto.parthenopeddit.model.Post;
import it.marcosautto.parthenopeddit.model.Review;
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

public class CoursesRequests {

    private Auth Auth;

    private ApiClient ApiClient;
    private ApiRoute ApiRoute;

    public ObservableList<Course> searchByName(String course_name) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8000/courses/search/"+course_name))
                    .setHeader("authorization", Auth.getInstance().getToken())
                    .build();

            HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            String jsonOutput = response.body();
            System.out.println(response.body());

            Type listType = new TypeToken<List<Course>>(){}.getType();
            List<Course> list = new ArrayList<Course>();
            list = gson.fromJson(jsonOutput, listType);
            ObservableList<Course> courses = FXCollections.observableList(list);

            return courses;
    }

    public ObservableList<Course> getFollowedCourses() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/courses/"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());

        Type listType = new TypeToken<List<Course>>(){}.getType();
        List<Course> list = new ArrayList<Course>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<Course> courses = FXCollections.observableList(list);

        return courses;
    }

    public Course getCourseByID(int course_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/courses/"+course_id))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        Course course = new Gson().fromJson(response.body(), Course.class);
        return course;
    }

    public int followCourse(int course_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/courses/"+Integer.toString(course_id)+"/follow"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public int unfollowCourse(int course_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/courses/"+Integer.toString(course_id)+"/unfollow"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public ObservableList<Post> getCoursePosts(int course_id, int page, int perPage, String transactionStartDataTime) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/courses/"+course_id+"/posts/"+perPage+"/"+page))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());
        Type listType = new TypeToken<List<Post>>(){}.getType();
        List<Post> list = new ArrayList<Post>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<Post> posts = FXCollections.observableList(list);
        return posts;

    }

    public ObservableList<Review> getCourseReviews(int course_id, int page, int perPage, String transactionStartDataTime) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/courses/"+course_id+"/reviews/"+perPage+"/"+page))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());
        Type listType = new TypeToken<List<Review>>(){}.getType();
        List<Review> list = new ArrayList<Review>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<Review> reviews = FXCollections.observableList(list);
        return reviews;

    }
}
