package it.marcosautto.parthenopeddit.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Post extends Content {

        private String title;
        private int posted_to_board_id;
        private Board posted_to_board;

        public Post(
                int id,
                String body,
                String timestamp,
                String authorId,
                User author,
                ArrayList<Comment> comments,
                int comments_num,
                int upvotes,
                int downvotes,

                String title,
                int idBoard,
                Board board
        ) {
            super(id, body, timestamp, authorId, "post", author, comments, comments_num, upvotes, downvotes);
            this.title = title;
            this.posted_to_board = board;
            this.posted_to_board_id = idBoard;
        }

        public String getTitle() { return title; }

        public void setTitle(String title) { this.title = title; }

        public Board getPostedToBoard(){ return posted_to_board; }

        public int getPostedToBoardId(){ return posted_to_board_id; }

        public void setComments(ArrayList<Comment> comments){ super.setComments(comments); }

}
