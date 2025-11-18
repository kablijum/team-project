package view;

import interface_adapter.post_review.PostController;
import interface_adapter.view_song.ViewSongController;
import interface_adapter.view_song.ViewSongViewModel;

import javax.swing.*;
import java.awt.*;


public class SongProfileView extends JPanel {
    private final ViewSongController viewSongController;
    private final PostController postController;
    private final ViewSongViewModel viewModel;

    private final JLabel songNameLabel = new JLabel();
    private final JLabel artistLabel = new JLabel();
    private final JLabel averageRatingLabel = new JLabel();

    private final JButton addReview = new JButton("Write a Review");
    private final JButton backButton = new JButton("Back");
    private final JButton refreshButton = new JButton("Refresh");

    public SongProfileView(ViewSongController viewSongController, ViewSongViewModel viewModel,  PostController postController) {
        this.viewSongController = viewSongController;
        this.viewModel = viewModel;
        this.postController = postController;


        setLayout(new BorderLayout());


        //  Song Information  //
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());
        songNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        labelPanel.add(songNameLabel, BorderLayout.WEST);

        artistLabel.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanel.add(artistLabel, BorderLayout.CENTER);

        labelPanel.add(backButton, BorderLayout.EAST);

        add(labelPanel, BorderLayout.NORTH);

        // Reviews, Average Rating and Add Review //
        JPanel reviewsPanel = new JPanel(new BorderLayout());
        JScrollPane reviewsScrollPane = new JScrollPane();
        reviewsPanel.add(reviewsScrollPane, BorderLayout.CENTER);
        // TODO: Add reviews from view model in in scrollpane()

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(averageRatingLabel);
        rightPanel.add(addReview);
        reviewsPanel.add(rightPanel, BorderLayout.EAST);

        add(reviewsPanel, BorderLayout.CENTER);

        //LISTENERS//
        addReview.addActionListener(e -> openWriteReviewDialog());
        backButton.addActionListener(e -> viewSongController.goBackToHome);

    }


    private void openWriteReviewDialog() {
        JDialog postReviewDialog = new JDialog((Frame) null, "Write a Review", true);
        postReviewDialog.setLocationRelativeTo(this);
        postReviewDialog.setSize(300, 250);
        postReviewDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Comment area as scrollpane //
        JTextArea comment = new JTextArea();
        JScrollPane commentScrollPane = new JScrollPane(comment);
        postReviewDialog.add(commentScrollPane, BorderLayout.CENTER);

        //Rating Dropdown menu//
        String[] ratings = {"1", "2", "3", "4", "5"};
        JComboBox<String> selected_ratings = new JComboBox<>(ratings);

        // Post Button //
        JButton postButton = new JButton("Post Review");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(postButton);

        postReviewDialog.setLayout(new BoxLayout(postReviewDialog, BoxLayout.Y_AXIS));
        postReviewDialog.add(commentScrollPane, BorderLayout.CENTER);
        postReviewDialog.add(selected_ratings, BorderLayout.SOUTH);

        postReviewDialog.add(buttonPanel);

        postReviewDialog.setVisible(true);



        postButton.addActionListener(e -> {
            String content = comment.getText();
            Object selected = selected_ratings.getSelectedItem();
            if (selected == null) {
                JOptionPane.showMessageDialog(postReviewDialog, "Please select a rating");
            }
            else {
                int rating = Integer.parseInt((String) selected);
                int songID = viewSongController.getSongId;
                String username = viewSongController.getUsername;
                postController.post(content, rating, username, songID);
                postReviewDialog.dispose();
            }
        });

    }
}
