package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {

    private String id;
    private String display_name;
    private String registered_on;

    private ObservableList<Content> published_content = FXCollections.observableArrayList();
    private ObservableList<Post> published_posts = FXCollections.observableArrayList();
    private ObservableList<Comment> published_comments = FXCollections.observableArrayList();
    private ObservableList<Review> published_reviews = FXCollections.observableArrayList();
    private ObservableList<Course> followed_courses = FXCollections.observableArrayList();
    private ObservableList<Group> groups = FXCollections.observableArrayList();
    private ObservableList<GroupInvite> group_invites = FXCollections.observableArrayList();

    public User(String id, String display_name, String registered_on, ObservableList<Content> published_content, ObservableList<Post> published_posts, ObservableList<Comment> published_comments, ObservableList<Review> published_reviews, ObservableList<Course> followed_courses, ObservableList<Group> groups, ObservableList<GroupInvite> group_invites){
        this.id = id;
        this.display_name = display_name;
        this.registered_on = registered_on;
        this.published_content = published_content;
        this.published_posts = published_posts;
        this. published_comments = published_comments;
        this.published_reviews = published_reviews;
        this.followed_courses = followed_courses;
        this.groups = groups;
        this.group_invites = group_invites;
    }

    public String getId(){ return id; }


    public ObservableList<Content> getPublishedContent(){ return published_content; }
    public ObservableList<Post> getPublishedPosts(){ return published_posts; }
    public ObservableList<Comment> getPublishedComments(){ return published_comments; }
    public ObservableList<Review> getPublishedReviews(){ return published_reviews; }
    public ObservableList<Course> getFollowedCourses(){ return followed_courses; }
    public ObservableList<Group> getGroups(){ return groups; }
    public ObservableList<GroupInvite> getGroupInvites(){ return group_invites; }

    public void addGroup(Group group){ groups.add(group);}
}
