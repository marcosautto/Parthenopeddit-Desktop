package it.marcosautto.parthenopeddit.api;

import it.marcosautto.parthenopeddit.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import it.marcosautto.parthenopeddit.*;

public class Mockdatabase {

    private static Mockdatabase instance;
    public static Mockdatabase getInstance() {
        return instance;
    }


    public ObservableList<User> users_table = FXCollections.observableArrayList();
    public ObservableList<Course> courses_table = FXCollections.observableArrayList();
    public ObservableList<Group> groups_table = FXCollections.observableArrayList();
    public ObservableList<Post> posts_table = FXCollections.observableArrayList();
    public ObservableList<Review> review_table = FXCollections.observableArrayList();
    public ObservableList<Comment> comments_table = FXCollections.observableArrayList();

    public ObservableList<Comment> noComments = FXCollections.observableArrayList();
    public ObservableList<Post> noPosts = FXCollections.observableArrayList();
    public ObservableList<Review> noReview = FXCollections.observableArrayList();
    public ObservableList<Content> noContent = FXCollections.observableArrayList();
    public ObservableList<Course> noCourse = FXCollections.observableArrayList();
    public ObservableList<Group> noGroups = FXCollections.observableArrayList();
    public ObservableList<GroupInvite> noGroupInvite = FXCollections.observableArrayList();
    public ObservableList<GroupMember> noGroupMember = FXCollections.observableArrayList();


    //public Course course1 = new Course("Programmazione 3", 4.5, 3.5, 11);
    //public Course course2 = new Course("Terminali mobili e multimedialità", 5.0, 2.5, 12);
//
//
//
    //public Group group1 = new Group(1, "Generale", "24/08/2020", noGroupMember, noGroupInvite);
    //public Group group2 = new Group(2, "CS Memes", "24/08/2020", noGroupMember, noGroupInvite);
    //public Group group3 = new Group(3, "Studenti L-21", "24/08/2020", noGroupMember, noGroupInvite);
    //public Group group4 = new Group(4, "Scienziati", "24/08/2020", noGroupMember, noGroupInvite);
    //public Group group5 = new Group(5, "Deals", "24/08/2020", noGroupMember, noGroupInvite);

    //USER
    //public ObservableList<Course> u1_courses = FXCollections.observableArrayList(course1, course2);
    //public ObservableList<Group> u1_groups = FXCollections.observableArrayList(group2, group3);

    //


    //public User user1 = new User("marcosautto", "Marco Sautto", "23/08/2020", noContent, noPosts, noComments, noReview, u1_courses, u1_groups, noGroupInvite);
    //public User user2 = new User("francescobottino", "Francesco Bottino", "23/08/2020", noContent, noPosts, noComments, noReview, noCourse, u1_groups, noGroupInvite);
    //public User user3 = new User("andrealombardi", "Andrea Lombardi", "24/08/2020", noContent, noPosts, noComments, noReview, noCourse, u1_groups, noGroupInvite);
//
    //public GroupInvite groupInvite1_u1 = new GroupInvite(user2.getId(), user1.getId(), group4.getId(), "24/08/2020", user2, user1, group4);
//
    //public GroupMember u1_member = new GroupMember(user1.getId(), group1.getBoardId(), "24/08/2020", true, user1, group1);
    //public GroupMember u2_member = new GroupMember(user2.getId(), group1.getBoardId(), "24/08/2020", false, user2, group1);
    //public GroupMember u3_member = new GroupMember(user3.getId(), group1.getBoardId(), "24/08/2020", false, user3, group1);

    public ObservableList<GroupMember> tempMembers = FXCollections.observableArrayList();

    //public Post post1 = new Post(0,"First post", "25/08/2020", user1.getId(), user1, "This is the first post here", group1, 1, 2, 0, noComments);
    //public Post post2 = new Post(1, "Second post", "25/08/2020", user1.getId(), user1, "This is the first post here", group1, 1, 5, 1, noComments);
    //public Post post3 = new Post(2, "Third post", "25/08/2020", user2.getId(), user2, "A regà bongiorno", group2, 2, 1, 10, noComments);
    //public Post post4 = new Post(3, "Autostop", "25/08/2020", user1.getId(), user1, "This is the first post here", course1, 11, 10, 0, noComments);

    //public Review review1 = new Review(10,"This is sheet","01/01/1970", user1.getId(), user1, noComments, 2, 0, course1.getCourseId(), 5, 3, course1);
    //public Review review2 = new Review(11,"This is sheet2","01/01/1970", user2.getId(), user2, noComments, 5, 2, course1.getCourseId(), 2, 4, course1);
    //public Review review3 = new Review(12, "This is sheet3","01/01/1970", user1.getId(), user1, noComments, 5, 2, course2.getCourseId(), 2, 4, course2);
    //public Review review4 = new Review(13,"Blue jeans","01/01/1970", user2.getId(), user2, noComments, 5, 2, course2.getCourseId(), 2, 4, course2);

    //public Comment comment1 = new Comment(1, "This is a comment", "25/08/2020", user1.getId(), user1, 0, 0, 0);
    //public Comment comment2 = new Comment(2, "This is a comment2", "25/08/2020", user1.getId(), user1, 0, 0, 0);
    //public Comment comment3 = new Comment(3, "This is a comment3", "25/08/2020", user2.getId(), user2, 0, 0, 1);
    //public Comment comment4 = new Comment(4, "This is a comment4", "25/08/2020", user1.getId(), user1, 0, 0, 11);


    //USER
    public ObservableList<Post> u1_post = FXCollections.observableArrayList(/*post1, post2, post4*/);
    public ObservableList<Review> u1_review = FXCollections.observableArrayList(/*review1, review3*/);
    public ObservableList<Comment> u1_comment = FXCollections.observableArrayList(/*comment1, comment2, comment4*/);

    //

    public Mockdatabase(){
        instance = this;
        //posts_table.addAll(post1, post2, post3, post4);
        //review_table.addAll(review1, review2, review3, review4);
        //users_table.addAll(user1, user2);
        //courses_table.addAll(course1, course2);
        //groups_table.addAll(group2, group3, group4, group5);
        //comments_table.addAll(comment1, comment2, comment3, comment4);
        //course1.getPosts().addAll(post1, post2);
        //course1.getReviews().addAll(review1, review2);
        //course1.setReviewsCount(course1.getReviews().size());

        //group1.getMembers().addAll(u1_member, u2_member, u3_member);
        //user1.addPosts(u1_post);
        //user1.addReviews(u1_review);
        //user1.addComments(u1_comment);
        //user1.addGroup(group4);
        //post1.addComment(comment1);
        //post1.addComment(comment2);
        //post2.addComment(comment3);
        //review1.addComment(comment4);

        //user1.addGroupInvite(groupInvite1_u1);
        //group1.addGroupInvite(groupInvite1_u1);
    }
}
