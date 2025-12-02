package view;

import interface_adapter.edit_review.EditReviewController;
import interface_adapter.edit_review.EditReviewViewModel;
import interface_adapter.view_profile_reviews.ProfileReviewsViewModel;
import interface_adapter.view_profile_reviews.ProfileReviewsController;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.edit_review.EditReviewSongDataAccessInterface;
import use_case.edit_review.EditUserDataAccessInterface;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class UserProfileView extends JPanel implements PropertyChangeListener {

    /** The view model holding the profile review data. */
    private final ProfileReviewsViewModel viewModel;
    /** The controller for general profile actions
     * (back, logout, change password). */
    private final ProfileReviewsController controller;
    /** The controller for editing reviews. */
    private final EditReviewController editController;
    /** The view model for handling review editing process. */
    private final EditReviewViewModel editViewModel;
    /** Data access interface for user data related to editing. */
    private final EditUserDataAccessInterface userData;
    /** Data access interface for song data related to editing. */
    private final EditReviewSongDataAccessInterface songData;
    /** The view model holding logged-in user state. */
    private final LoggedInViewModel loggedInViewModel;

    /** Button to return to the home screen. */
    private final JButton backButton = new JButton("Back to Home");
    /** Button to initiate logout. */
    private final JButton logoutButton = new JButton("Logout");
    /** Button to initiate password change dialog. */
    private final JButton changePasswordButton = new JButton("Change Password");
    /** Label to display the username. */
    private final JLabel usernameLabel = new JLabel();

    /** Label indicating the reviews section. */
    private final JLabel myReviewsLabel = new JLabel("My Reviews:");
    /** Button to initiate the edit review action. */
    private final JButton editButton = new JButton("Edit");

    /** Model for the JList displaying reviews. */
    private final DefaultListModel<String> reviewListModel =
            new DefaultListModel<>();
    /** The list component displaying reviews. */
    private final JList<String> reviewList = new JList<>(reviewListModel);

    /** The static name identifier for this view. */
    public static final String VIEW_NAME = "profile";

    public UserProfileView(final ProfileReviewsViewModel viewModel,
                           final ProfileReviewsController controller,
                           final EditReviewController editController,
                           final EditReviewViewModel editViewModel,
                           final EditUserDataAccessInterface userData,
                           final EditReviewSongDataAccessInterface songData,
                           final LoggedInViewModel loggedInViewModel) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.editController = editController;
        this.editViewModel = editViewModel;
        this.userData = userData;
        this.songData = songData;
        this.loggedInViewModel = loggedInViewModel;

        this.viewModel.addPropertyChangeListener(this);

        //Edit Listener
        this.editViewModel.addPropertyChangeListener(this);

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

        // Middle：My Reviews:      [Edit] [Delete]
        JPanel middleBar = new JPanel(new BorderLayout());
        middleBar.add(myReviewsLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(editButton);
        middleBar.add(buttonPanel, BorderLayout.EAST);

        // Reviews List
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(middleBar, BorderLayout.NORTH);

        reviewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(reviewList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // call controller
        backButton.addActionListener(e -> controller.goBackToHome());
        changePasswordButton.addActionListener(e -> showChangePasswordDialog());
        logoutButton.addActionListener(e -> controller.logout());
        editButton.addActionListener(e -> {
            int index = reviewList.getSelectedIndex();
            if (index >= 0) {
                editReviewAt(index);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a review to edit.",
                        "No review selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        refresh();
    }
    /**
     * Opens a dialog to edit the review at the specified index.
     * @param index the index of the review in the list
     */
    public void editReviewAt(final int index) {
        List<ProfileReviewsViewModel.ReviewRow> reviewRows
                = viewModel.getReviews();
        if (index < 0 || index >= reviewRows.size()) {
            return;
        }

        ProfileReviewsViewModel.ReviewRow reviewRow = reviewRows.get(index);

        JDialog editDialog = new JDialog((Frame) null, "Edit Review", true);
        editDialog.setLocationRelativeTo(this);
        editDialog.setSize(400, 300);
        editDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        editDialog.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel songInfoLabel = new JLabel(
                "Editing review for: " + reviewRow.getSongTitle());
        songInfoLabel.setFont(new Font("Arial",  Font.BOLD, 12));
        mainPanel.add(songInfoLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));

        JLabel commentLabel = new JLabel("Comment:");
        JTextArea commentArea = new JTextArea(reviewRow.getComment());
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        JScrollPane commentScrollPane = new JScrollPane(commentArea);
        commentScrollPane.setPreferredSize(new Dimension(350, 150));

        JPanel commentPanel = new JPanel(new BorderLayout(5, 5));
        commentPanel.add(commentLabel, BorderLayout.NORTH);
        commentPanel.add(commentScrollPane, BorderLayout.CENTER);
        centerPanel.add(commentPanel, BorderLayout.CENTER);

        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel ratingLabel = new JLabel("Rating:");
        String[] ratings = {"1", "2", "3", "4", "5"};
        JComboBox<String> ratingComboBox = new JComboBox<>(ratings);
        ratingComboBox.setSelectedItem(String.valueOf(reviewRow.getRating()));
        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingComboBox);
        centerPanel.add(ratingPanel, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        editDialog.add(mainPanel);

        saveButton.addActionListener(e -> {
            String newComment = commentArea.getText();
            String selectedRating = (String) ratingComboBox.getSelectedItem();
            int newRating = Integer.parseInt(selectedRating);
            String username = viewModel.getUsername();
            int songId = reviewRow.getSongID();

            if (newComment.isEmpty()) {
                JOptionPane.showMessageDialog(editDialog,
                        "Comment cannot be empty",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            editController.execute(newComment, newRating,
                    username, songId, index);
            editDialog.dispose();
            refresh();
        });

        cancelButton.addActionListener(e -> editDialog.dispose());

        editDialog.setVisible(true);
    }

    private void showChangePasswordDialog() {
                JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

                JDialog dialog = new JDialog(owner,
                        "Change your password", true);

                JPanel panel = new JPanel(new BorderLayout());

                JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel label = new JLabel("New password: ");
                JPasswordField newPasswordField = new JPasswordField(15);
                inputPanel.add(label);
                inputPanel.add(newPasswordField);
                panel.add(inputPanel, BorderLayout.CENTER);

                JPanel buttonsPanel =
                        new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton updateButton = new JButton("Update password");
                JButton doneButton = new JButton("return");
                buttonsPanel.add(updateButton);
                buttonsPanel.add(doneButton);
                panel.add(buttonsPanel, BorderLayout.SOUTH);

                dialog.setContentPane(panel);
                dialog.pack();
                dialog.setLocationRelativeTo(this);

                updateButton.addActionListener(ev -> {
                    String newPassword =
                            new String(newPasswordField.getPassword()).trim();
                    if (newPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog,
                                "Password cannot be empty.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String username =
                            loggedInViewModel.getState().getUsername();

                    controller.changePassword(newPassword, username);

                    JOptionPane.showMessageDialog(dialog,
                            "Password updated.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    newPasswordField.setText("");
                });

                doneButton.addActionListener(ev -> dialog.dispose());

                dialog.setVisible(true);
            }

    /**
     * Handles property change events from the ViewModels.
     * @param evt the property change event
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getSource() == this.viewModel) {
            refresh();
        } else if ("editSuccess".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this,
                    "Review updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            String username = viewModel.getUsername();
            controller.openProfile(username);
        } else if ("editFail".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this,
                    editViewModel.getErrorMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //call from viewModel
    /**
     * Updates the view components with the latest data from the ViewModel.
     */
    public void refresh() {
        usernameLabel.setText(viewModel.getUsername());
        reviewListModel.clear();
        for (ProfileReviewsViewModel.ReviewRow r : viewModel.getReviews()) {
            String line = String.format(
                    "Song: %s  |  Your rating: %d  |  Comment: %s",
                    r.getSongTitle(),
                    r.getRating(),
                    r.getComment()
            );
            reviewListModel.addElement(line);
        }
    }
}
