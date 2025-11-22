package view;

import entity.Review;
import interface_adapter.view_profile_reviews.ProfileReviewsViewModel;
import interface_adapter.view_profile_reviews.ProfileReviewsController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserProfileView extends JPanel {

    private final ProfileReviewsViewModel viewModel;
    private final ProfileReviewsController controller;

    private final JButton backButton = new JButton("Back to Home");
    private final JButton logoutButton = new JButton("Logout");
    private final JLabel usernameLabel = new JLabel();

    private final JLabel myReviewsLabel = new JLabel("My Reviews:");
    private final JButton deleteButton = new JButton("Delete");

    private final DefaultListModel<String> reviewListModel = new DefaultListModel<>();
    private final JList<String> reviewList = new JList<>(reviewListModel);

    public UserProfileView(ProfileReviewsViewModel viewModel,
                           ProfileReviewsController controller) {
        this.viewModel = viewModel;
        this.controller = controller;

        setLayout(new BorderLayout());

        // Top：Back | username | Logout
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.add(backButton, BorderLayout.WEST);

        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topBar.add(usernameLabel, BorderLayout.CENTER);

        topBar.add(logoutButton, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        // Middle：My Reviews:      [Delete]
        JPanel middleBar = new JPanel(new BorderLayout());
        middleBar.add(myReviewsLabel, BorderLayout.WEST);
        middleBar.add(deleteButton, BorderLayout.EAST);

        // Reviews List
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(middleBar, BorderLayout.NORTH);

        reviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(reviewList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // call controller
        backButton.addActionListener(e -> controller.goBackToHome());
        logoutButton.addActionListener(e -> controller.logout());
        deleteButton.addActionListener(e -> {
            int index = reviewList.getSelectedIndex();
            if (index >= 0) {
                controller.deleteReviewAt(index);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a review to delete.",
                        "No review selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        refresh();
    }

    //call from viewModel
    public void refresh() {
        usernameLabel.setText(viewModel.getUsername());

        reviewListModel.clear();
        List<Review> reviews = viewModel.getReviews();
        for (Review r : reviews) {
            String line = String.format(
                    "%s  |  Your rating: %d  |  %s",
                    r.getSong().getName(),
                    r.getRating(),
                    r.getComment()
            );
            reviewListModel.addElement(line);
        }
    }

    //test
    public static void main(String[] args) {
        // 1. fake viewModel
        ProfileReviewsViewModel vm = new ProfileReviewsViewModel();
        vm.setUsername("Connie");

        java.util.List<entity.Review> fakeReviews = new java.util.ArrayList<>();

        fakeReviews.add(new entity.Review(null, "Loved it!", 1, 5, 10));
        fakeReviews.add(new entity.Review(null, "Nice rhythm", 2, 4, 3));

        vm.setReviews(fakeReviews);

        // call controller
        ProfileReviewsController controller = new ProfileReviewsController();

        // panel
        JFrame frame = new JFrame("User Profile Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new UserProfileView(vm, controller));
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
