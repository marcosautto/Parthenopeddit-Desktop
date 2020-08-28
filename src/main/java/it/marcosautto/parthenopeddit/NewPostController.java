package it.marcosautto.parthenopeddit;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewPostController {
        @FXML
        public Label boardNameLabel;

        @FXML
        public TextField titleTextArea;

        @FXML
        public TextArea postTextArea;

        @FXML
        public Button cancelButton;

        @FXML
        public Button publishButton;

        private static NewPostController instance;
        public NewPostController() {
            instance = this;
        }

        public static NewPostController getInstance() {
            return instance;
        }
/*
        public Course course;

        public void transferMessage(int courseId) {

            Course mCourse = Mockdatabase.getInstance().courses_table.stream().filter(course -> course.getCourseId() == courseId).collect(Collectors.toList()).get(0);
            course = mCourse;
            courseNameLabel.setText(mCourse.getName());
        }

        public void handlePublish() throws IOException {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            Review review = new Review(reviewTextArea.getText(), formatter.format(date), Mockdatabase.getInstance().user1.getId(), Mockdatabase.getInstance().user1,  Mockdatabase.getInstance().noComments, 0, 0, course.getCourseId(), (int) average_enjoyment_score_ratingbar.getRating(), (int) average_difficulty_score_ratingbar.getRating(), course);
            Mockdatabase.getInstance().courses_table.stream().filter(course -> course.getCourseId() == course.getCourseId()).collect(Collectors.toList()).get(0).getReviews().add(review);
            System.out.println(review.getReviewedCourse().getName());
            System.out.println(review.getReviewedCourse().getReviews().size()+ " inside");
            DashboardController.getInstance().courseSelected(course.getCourseId());
        }

        public void handleCancel() throws IOException{
            DashboardController.getInstance().courseSelected(course.getCourseId());
        }

*/
}
