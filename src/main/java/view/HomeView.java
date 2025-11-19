package view;


import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * The View for when the user is logged into the program and enters the home page.
 */
public class HomeView extends JPanel implements PropertyChangeListener {
    public static final String VIEW_NAME = "home";
    private final LoggedInViewModel loggedInViewModel;

    public HomeView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        // Header content
        JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JButton profileButton = new JButton("My Profile");
        header.add(title, BorderLayout.WEST);
        header.add(profileButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Main functionalities
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Logo
        java.net.URL logoUrl = getClass().getResource("/images/IMG_1233.PNG");
        JLabel logoLabel;

        if (logoUrl != null) {
            ImageIcon originalIcon = new ImageIcon(logoUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    400, 400, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(scaledImage));
        } else {
            logoLabel = new JLabel("LOGO"); // fallback if image not found
        }

        JPanel logoWrapper = new JPanel(new GridBagLayout());
        logoWrapper.setOpaque(false);
        logoWrapper.add(logoLabel);

        center.add(Box.createVerticalGlue());
        center.add(searchPanel);
        center.add(Box.createRigidArea(new Dimension(0, 15)));
        center.add(logoWrapper);
        center.add(Box.createVerticalGlue());

        add(center, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

}
