package sample.model;

import javafx.collections.ObservableList;

public class Post extends Content {

    //TODO: optimize extends

        private String title;
        private int posted_to_board_id;
        private Board posted_to_board;

        public Post(int id, String title, String timestamp, String authorId, User author, String body, Board board, int idBoard, int upvotes, int downvotes, ObservableList<Comment> comments) {
            super(id, body, timestamp, authorId, "post", author, upvotes, downvotes, comments);
            this.title = title;
            this.posted_to_board = board;
            this.posted_to_board_id = idBoard;
        }

        public String getTitle() { return title; }

        public void setTitle(String title) { this.title = title; }

        public Board getPostedToBoard(){ return posted_to_board; }

        public int getPostedToBoardId(){ return posted_to_board_id; }

}
