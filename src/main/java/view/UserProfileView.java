package view;

import interface_adapter.logout.LogoutController;
import interface_adapter.view_profile_reviews.ProfileReviewsViewModel;
import interface_adapter.view_profile_reviews.ProfileReviewsController;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserProfileView extends JPanel {

    private final ProfileReviewsViewModel viewModel;
    private final ProfileReviewsController controller;

    private final JButton backButton = new JButton("Back to Home");
    private final JButton logoutButton = new JButton("Logout");
    private final JButton changePasswordButton = new JButton("Change Password");
    private final JLabel usernameLabel = new JLabel();

    private final JLabel myReviewsLabel = new JLabel("My Reviews:");
    private final JButton editButton = new JButton("Edit");

    private final DefaultListModel<String> reviewListModel = new DefaultListModel<>();
    private final JList<String> reviewList = new JList<>(reviewListModel);

    public static final String VIEW_NAME = "profile";

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

//        topBar.add(logoutButton, BorderLayout.EAST);
//        add(topBar, BorderLayout.NORTH);

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtons.add(changePasswordButton);
        rightButtons.add(logoutButton);

        topBar.add(rightButtons, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        // Middle：My Reviews:      [Edit]
        JPanel middleBar = new JPanel(new BorderLayout());
        middleBar.add(myReviewsLabel, BorderLayout.WEST);
        middleBar.add(editButton, BorderLayout.EAST);

        // Reviews List
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(middleBar, BorderLayout.NORTH);

        reviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(reviewList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // call controller
        backButton.addActionListener(e -> controller.goBackToHome());
        changePasswordButton.addActionListener(e -> controller.changePassword());
        logoutButton.addActionListener(e -> controller.logout());
        editButton.addActionListener(e -> {
            int index = reviewList.getSelectedIndex();
            if (index >= 0) {
                controller.editReviewAt(index);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a review to edit.",
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
        List<ProfileReviewsViewModel.ReviewRow> reviews = viewModel.getReviews();
        for (ProfileReviewsViewModel.ReviewRow r : reviews) {
            String line = String.format(
                    "%s  |  Your rating: %d  |  %s",
                    r.getSongTitle(),
                    r.getRating(),
                    r.getComment()
            );
            reviewListModel.addElement(line);
        }
    }

    //test
    //public static void main(String[] args) {
        // 1. fake viewModel
    //    ProfileReviewsViewModel vm = new ProfileReviewsViewModel();
    //   vm.setUsername("Connie");
    //
    //    java.util.List<ProfileReviewsViewModel.ReviewRow> fake = new java.util.ArrayList<>();
    //    fake.add(new ProfileReviewsViewModel.ReviewRow("Song A", 5, "Loved it!"));
    //    fake.add(new ProfileReviewsViewModel.ReviewRow("Song B", 4, "Nice rhythm"));
    //
    //    vm.setReviews(fake);
    //
        // call controller
    //    ProfileReviewsController controller =
    //            new ProfileReviewsController(new ViewManagerModel());

        // panel
    //    JFrame frame = new JFrame("User Profile Test");
    //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //    frame.setContentPane(new UserProfileView(vm, controller));
    //    frame.setSize(800, 600);
    //    frame.setLocationRelativeTo(null);
    //    frame.setVisible(true);
    //}
}
