package sample.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.*;

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

    public ObservableList<Comment> noComments = FXCollections.observableArrayList();
    public ObservableList<Post> noPosts = FXCollections.observableArrayList();
    public ObservableList<Review> noReview = FXCollections.observableArrayList();
    public ObservableList<Content> noContent = FXCollections.observableArrayList();
    public ObservableList<Course> noCourse = FXCollections.observableArrayList();
    public ObservableList<Group> noGroups = FXCollections.observableArrayList();
    public ObservableList<GroupInvite> noGroupInvite = FXCollections.observableArrayList();
    public ObservableList<GroupMember> noGroupMember = FXCollections.observableArrayList();


    public Course course1 = new Course("Programmazione 3", 4.5, 3.5, 0);
    public Course course2 = new Course("Terminali mobili e multimedialità", 5.0, 2.5, 1);



    public Group group1 = new Group(2, "CS Memes", "24/08/2020", noGroupMember, noGroupInvite);
    public Group group2 = new Group(3, "Studenti L-21", "24/08/2020", noGroupMember, noGroupInvite);
    public Group group3 = new Group(4, "Scienziati", "24/08/2020", noGroupMember, noGroupInvite);

    //USER
    public ObservableList<Course> u1_courses = FXCollections.observableArrayList(course1, course2);
    public ObservableList<Group> u1_groups = FXCollections.observableArrayList(group1, group2);

    //

    public User user1 = new User("marcosautto", "Marco Sautto", "23/08/2020", noContent, noPosts, noComments, noReview, u1_courses, u1_groups, noGroupInvite);
    public User user2 = new User("francescobottino", "Francesco Bottino", "23/08/2020", noContent, noPosts, noComments, noReview, noCourse, noGroups, noGroupInvite);

    public GroupMember u1_member = new GroupMember(user1.getId(), group1.getBoardId(), "24/08/2020", true, user1, group1);
    public ObservableList<GroupMember> tempMembers = FXCollections.observableArrayList();

    public Post post1 = new Post("First post", Post.CONTENT.COURSE, user1, "This is the first post here", 0);
    public Post post2 = new Post("Second post", Post.CONTENT.COURSE, user1, "This is the first post here", 0);



    public Review review1 = new Review("This is sheet","01/01/1970", user1.getId(), user1, noComments, 2, 0, course1.getCourseId(), 5, 3, course1);
    public Review review2 = new Review("This is sheet2","01/01/1970", user2.getId(), user1, noComments, 5, 2, course1.getCourseId(), 2, 4, course1);


    public Mockdatabase(){
        instance = this;
        users_table.addAll(user1, user2);
        courses_table.addAll(course1, course2);
        groups_table.addAll(group1, group2, group3);
        course1.getPosts().addAll(post1, post2);
        course1.getReviews().addAll(review1, review2);
        course1.setReviewsCount(course1.getReviews().size());

        group1.getMembers().add(u1_member);
        user1.addGroup(group3);
    }
}
