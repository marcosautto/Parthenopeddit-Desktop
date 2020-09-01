package it.marcosautto.parthenopeddit.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import it.marcosautto.parthenopeddit.model.*;
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

public class GroupsRequests {

    private Auth Auth;
    private ApiClient ApiClient;
    private ApiRoute ApiRoute;

    public ObservableList<GroupMember> getUserGroups() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/groups/"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());

        //List<Group> pdvs = gson.fromJson(reader, new TypeToken<List<PdvResult>>() {}.getType());

        Type listType = new TypeToken<ArrayList<GroupMember>>(){}.getType();
        ArrayList<GroupMember> list = new ArrayList<GroupMember>();
        list = gson.fromJson(jsonOutput, listType);

        ObservableList<GroupMember> groups = FXCollections.observableList(list);
        return groups;
    }

    public Group createGroup(String group_name, List<String> invitedUserIds) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();

        Gson Gson = new Gson();

        data.put("group_name", group_name);
        data.put("invited_members", Gson.toJson(invitedUserIds));       //prob crash

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/groups/"))
                .setHeader("authorization", it.marcosautto.parthenopeddit.api.Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Group group = new Gson().fromJson(response.body(), Group.class);

        return group;
    }

    public ObservableList<GroupInvite> getUserInvitesToGroup() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/groups/invites"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        String jsonOutput = response.body();
        System.out.println(response.body());

        Type listType = new TypeToken<List<GroupInvite>>(){}.getType();
        List<GroupInvite> list = new ArrayList<GroupInvite>();
        list = gson.fromJson(jsonOutput, listType);
        ObservableList<GroupInvite> groupInvites = FXCollections.observableList(list);

        return groupInvites;
    }

    public Group getGroup(int group_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/groups/"+group_id))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        Group group = new Gson().fromJson(response.body(), Group.class);
        return group;
    }

    public ObservableList<GroupInvite> inviteUsersToGroup(int group_id, List<String> invitedUsersIds) throws IOException, InterruptedException{
        Map<Object, Object> data = new HashMap<>();

        Gson Gson = new Gson();

        data.put("users_list", Gson.toJson(invitedUsersIds));       //prob crash

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/invite"))
                .setHeader("authorization", it.marcosautto.parthenopeddit.api.Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Type listType = new TypeToken<List<GroupInvite>>() {}.getType();
        List<GroupInvite> list = new ArrayList<GroupInvite>();
        list = Gson.fromJson(response.body(), listType);
        ObservableList<GroupInvite> groupInvites = FXCollections.observableList(list);

        return groupInvites;
    }

    public int undoInvite(int group_id, String user_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();      //prob crash

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/invite/"+user_id+"/undo"))
                .setHeader("authorization", it.marcosautto.parthenopeddit.api.Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public ObservableList<GroupInvite> getGroupInvites(int group_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/invite"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson Gson = new Gson();
        Type listType = new TypeToken<List<GroupInvite>>() {}.getType();
        List<GroupInvite> list = new ArrayList<GroupInvite>();
        list = Gson.fromJson(response.body(), listType);
        ObservableList<GroupInvite> groupInvites = FXCollections.observableList(list);

        return groupInvites;
    }

    public ObservableList<User> searchInvitableUser(int group_id, String searched_user_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/invite/search/"+searched_user_id))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson Gson = new Gson();
        Type listType = new TypeToken<List<User>>() {}.getType();
        List<User> list = new ArrayList<User>();
        list = Gson.fromJson(response.body(), listType);
        ObservableList<User> invitableUsers = FXCollections.observableList(list);

        return invitableUsers;
    }

    private int answerGroupInvite(int group_id, boolean accept) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();      //prob crash
        Gson Gson = new Gson();
        data.put("answer", Gson.toJson(accept));

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/invite/answer"))
                .setHeader("authorization", it.marcosautto.parthenopeddit.api.Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    private int leaveGroup(int group_id) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();      //prob crash

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/leave"))
                .setHeader("authorization", it.marcosautto.parthenopeddit.api.Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public ObservableList<GroupMember> getGroupMembers(int group_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/members"))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        Gson Gson = new Gson();
        Type listType = new TypeToken<List<GroupMember>>(){}.getType();
        List<GroupMember> list = new ArrayList<GroupMember>();
        list = Gson.fromJson(response.body(), listType);
        ObservableList<GroupMember> groupMembers = FXCollections.observableList(list);

        return groupMembers;
    }

    private int removeFromGroup(int group_id, String userId) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();      //prob crash

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/kick/"+userId))
                .setHeader("authorization", it.marcosautto.parthenopeddit.api.Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    private int makeMembersOwners(int group_id, List<String> newOwners) throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();      //prob crash
        Gson Gson = new Gson();
        data.put("users_list", Gson.toJson(newOwners));

        HttpRequest request = HttpRequest.newBuilder()
                .POST(BuildFormDataFromMap.build(data))
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/members/make_owner"))
                .setHeader("authorization", it.marcosautto.parthenopeddit.api.Auth.getInstance().getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public ObservableList<Post> getGroupPosts(int group_id, int per_page, int page, String transactionStartDateTime) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000/groups/"+group_id+"/posts/"+per_page+"/"+page))
                .setHeader("authorization", Auth.getInstance().getToken())
                .build();

        HttpResponse<String> response = ApiClient.getInstance().getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        Gson Gson = new Gson();
        Type listType = new TypeToken<List<Post>>(){}.getType();
        List<Post> list = new ArrayList<Post>();
        list = Gson.fromJson(response.body(), listType);
        ObservableList<Post> posts = FXCollections.observableList(list);

        return posts;
    }



}
