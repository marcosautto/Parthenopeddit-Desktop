package sample.model;

public class Post /*extends Content*/ {

    //TODO: optimize extends

        private static int postIdAct = 0;
        private int postId;
        private String title;
        private String authorId;
        private User author;
        private String postContent;
        private int upvote;
        private int downvote;
        private CONTENT content;
        private int posted_to_board_id;

    public enum CONTENT {
        HOME,
        GROUP,
        COURSE
    }

        public Post(String title, CONTENT content, User author, String postContent, int idBoard) {
            postId = postIdAct++;
            this.title = title;
            this.content = content;
            this.authorId = author.getId();
            this.author = author;
            this.postContent = postContent;
            upvote = 0;
            downvote = 0;
            this.posted_to_board_id = idBoard;
        }

        public int getPostId() {
            return postId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public CONTENT getContent() {
            return content;
        }

        public void setContent(CONTENT content) {
            this.content = content;
        }

        public String getAuthorId() { return authorId; }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getPostContent(){ return postContent; }

        public int getUpvote(){ return upvote; }

        public int getDownvote(){ return downvote; }

}
