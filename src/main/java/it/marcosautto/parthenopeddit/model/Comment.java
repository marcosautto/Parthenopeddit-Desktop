package it.marcosautto.parthenopeddit.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Comment extends Content{
    private int commented_content_id;
    private int root_content_id;

    public Comment(int id, String body, String timestamp, String author_id, User author, ArrayList<Comment> comments, int comments_num, int likes_num, int dislikes_num, int commented_content_id, int root_content_id){
        super(id, body, timestamp, author_id, "comment", author, comments, comments_num, likes_num, dislikes_num);
        this.commented_content_id = commented_content_id;
        this.root_content_id = root_content_id;
    }

    private int getCommentedContentId(){ return commented_content_id; }
}
