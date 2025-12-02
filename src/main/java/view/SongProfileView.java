package view;

import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.post_review.PostController;
import interface_adapter.post_review.PostViewModel;
import interface_adapter.upvote_review.UpvoteController;
import interface_adapter.view_song.ReviewViewModelItem;
import interface_adapter.view_song.ViewSongController;
import interface_adapter.view_song.ViewSongViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.util.List;


public class SongProfileView extends JPanel {
    private final ViewSongController viewSongController;
    private final PostController postController;
    private final UpvoteController upvoteController;
    private final ViewSongViewModel viewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final PostViewModel postViewModel;

    private final JLabel songNameLabel = new JLabel();
    private final JLabel artistLabel = new JLabel();
    private final JLabel averageRatingLabel = new JLabel();

    private final JButton addReview = new JButton("Write a Review");
    private final JButton backButton = new JButton("Back to Home");
    private final JButton upvoteButton = new JButton("Upvote this review");

    private JList<ReviewViewModelItem> reviewList;

    public SongProfileView(final ViewSongController viewSongController,
                           final ViewSongViewModel viewModel,
                           final PostController postController,
                           final UpvoteController upvoteController,
                           final LoggedInViewModel loggedInViewModel,
                           final PostViewModel postViewModel) {
        this.viewSongController = viewSongController;
        this.viewModel = viewModel;
        this.postController = postController;
        this.upvoteController = upvoteController;
        this.loggedInViewModel = loggedInViewModel;
        this.postViewModel = postViewModel;


        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);


        // Reviews, Average Rating and Add Review //
        JPanel reviewsPanel = getReviewsPanel();

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        if (viewModel.getState().getAverageRating() != 0) {
            averageRatingLabel.setText("Average Rating:" + "\n" + viewModel.getState().getAverageRating());
            averageRatingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            averageRatingLabel.setFont(new Font("Serif", Font.BOLD, 12));
        } else {
            averageRatingLabel.setText(viewModel.getState().getMessage());
        }

        rightPanel.add(averageRatingLabel);
        rightPanel.add(addReview);
        reviewsPanel.add(rightPanel, BorderLayout.EAST);

        add(reviewsPanel, BorderLayout.CENTER);

        //LISTENERS//
        addReview.addActionListener(e -> {
            openWriteReviewDialog();
        });

        backButton.addActionListener(e -> viewSongController.goBackToHome());


        viewModel.addPropertyChangeListener(evt -> {

            // update label
            songNameLabel.setText(viewModel.getState().getSongName());
            artistLabel.setText(viewModel.getState().getArtist());
            if (viewModel.getState().getAverageRating() != 0) {
                averageRatingLabel.setText("Average Rating:" + "\n" + viewModel.getState().getAverageRating());
            } else {
                averageRatingLabel.setText(viewModel.getState().getMessage());
            }

            // Update review list
            DefaultListModel<ReviewViewModelItem> listModel = new DefaultListModel<>();
            System.out.println("Refreshing review list...");
            List<ReviewViewModelItem> reviews = viewModel.getState().getReviews();
            System.out.println("Number of reviews: " + (reviews != null ? reviews.size() : "null"));
            if (reviews != null) {
                for (ReviewViewModelItem item : reviews) {
                    listModel.addElement(item);
                }
            }
            reviewList.setModel(listModel);

            revalidate();
            repaint();
        });
    }


    private JPanel getReviewsPanel() {
        JPanel reviewsPanel = new JPanel(new BorderLayout());
        reviewsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
                new EmptyBorder(15, 15, 15, 15)));

        JLabel reviewsTitle = new JLabel("Reviews");
        reviewsTitle.setFont(new Font("Serif", Font.BOLD, 20));
        reviewsTitle.setBorder(new EmptyBorder(0, 0, 10, 0));


        DefaultListModel<ReviewViewModelItem> listModel = new DefaultListModel<>();
        List<ReviewViewModelItem> reviews = viewModel.getState().getReviews();
        if (reviews == null) {
            reviews = List.of();
        }
        for (ReviewViewModelItem item : reviews) {
            listModel.addElement(item);
        }

        this.reviewList = new JList<>(listModel);
        reviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reviewList.setFont(new Font("Serif", Font.PLAIN, 14));
        reviewList.setBorder(new EmptyBorder(5, 5, 5, 5));
        reviewList.addListSelectionListener( e -> {
                    ReviewViewModelItem item = reviewList.getSelectedValue();
                    if (item != null) {
                        this.showReviewPopup(item);
                    }
                });
        reviewList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                JPanel panel = new JPanel(new BorderLayout(10,0));
                panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

                ReviewViewModelItem item = (ReviewViewModelItem) value;
                JLabel reviewLabel = new JLabel(item.toString());
                reviewLabel.setFont(new Font("Serif", Font.BOLD, 14));

                JLabel upvoteLabel = new JLabel("Upvotes: " + item.getUpvotes());
                upvoteLabel.setFont(new Font("Serif", Font.BOLD, 14));
                upvoteLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

                panel.add(reviewLabel, BorderLayout.WEST);
                panel.add(upvoteLabel, BorderLayout.EAST);

                return panel;
            }
        });

        JScrollPane scrollPane = new JScrollPane(reviewList);
        scrollPane.setBorder(null);



        reviewsPanel.add(reviewsTitle,BorderLayout.NORTH);
        reviewsPanel.add(reviewList, BorderLayout.CENTER);
        return reviewsPanel;
    }

    public JPanel createHeaderPanel(){
        JPanel headerPanel = new JPanel(new BorderLayout(10,10));
        headerPanel.setBorder (new EmptyBorder(20,20,20,20));

        songNameLabel.setText("'" + viewModel.getState().getSongName() + "'");
        songNameLabel.setFont(new Font("Serif",Font.BOLD,20));

        artistLabel.setText("By:" + viewModel.getState().getArtist());
        artistLabel.setFont(new Font("Serif",Font.BOLD,15));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(songNameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(artistLabel);

        headerPanel.add(infoPanel, BorderLayout.CENTER);
        headerPanel.add(backButton, BorderLayout.EAST);

        return headerPanel;

    }

    private void openWriteReviewDialog() {
        JDialog postReviewDialog = new JDialog((Frame) null, "Write a Review", true);
        postReviewDialog.setLocationRelativeTo(this);
        postReviewDialog.setSize(400, 300);
        postReviewDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        // Comment area as scrollpane //
        JTextArea comment = new JTextArea();
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        JScrollPane commentScrollPane = new JScrollPane(comment);


        //Rating Dropdown menu//
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratingPanel.add(new JLabel("Rating:"));
        String[] ratings = {"1", "2", "3", "4", "5"};
        JComboBox<String> selectedRatings = new JComboBox<>(ratings);
        ratingPanel.add(selectedRatings);

        // Post Button //
        JButton postButton = new JButton("Post Review");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(postButton);

        contentPanel.add(new JLabel("Write your review:"));
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(commentScrollPane);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(ratingPanel);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(buttonPanel);

        postReviewDialog.add(contentPanel);


        postButton.addActionListener(e -> {
            String content = comment.getText();
            Object selected = selectedRatings.getSelectedItem();

            if (selected == null) {
                JOptionPane.showMessageDialog(postReviewDialog,
                        "Please select a rating");
            } else {
                int rating = Integer.parseInt((String) selected);
                int songID = viewModel.getState().getSongId();
                String username = loggedInViewModel.getState().getUsername();
                postController.execute(content, rating, username, songID);

                if (postViewModel != null &&
                        postViewModel.getState().getErrorMessage() != null) {
                    JOptionPane.showMessageDialog(
                            postReviewDialog,
                            postViewModel.getState().getErrorMessage());
                }

                postViewModel.getState().setErrorMessage(null);
            }
            postReviewDialog.dispose();
            refresh();

        });
        postReviewDialog.setVisible(true);

    }
    public void showReviewPopup(ReviewViewModelItem review) {
        JDialog dialog = new JDialog((Frame) null, "Review", true);
        dialog.setLocationRelativeTo(this);
        dialog.setSize(300, 250);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JTextArea info = new JTextArea( "Review by: " + review.getUsername()
                + "\n" + review.getComment()
                + "\n" +  "Rating:" + review.getRating());
        JScrollPane reviewScrollPane = new JScrollPane(info);
        dialog.add(reviewScrollPane, BorderLayout.CENTER);
        dialog.add(upvoteButton, BorderLayout.SOUTH);


        upvoteButton.addActionListener(e -> {
            upvoteController.execute(
                    loggedInViewModel.getState().getUsername(),
                    review.getUsername(), viewModel.getState().getSongId());
            refresh();
            dialog.dispose();
        });
        dialog.setVisible(true);
    }

    public void refresh() {
        int songid = viewModel.getState().getSongId();
        viewSongController.execute(songid);


    }

}
