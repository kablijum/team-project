package view;

import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchViewModel;
import interface_adapter.view_song.ViewSongState;
import interface_adapter.view_song.ViewSongViewModel;
import interface_adapter.ViewManagerModel;
import use_case.search.SearchOutputData;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * The View for when the user is logged into the program and enters the home page.
 */
public class HomeView extends JPanel implements PropertyChangeListener {
    public static final String VIEW_NAME = "home";
    private final LoggedInViewModel loggedInViewModel;
    private final SearchViewModel searchViewModel;
    private final SearchController searchController;
    private final ViewSongViewModel viewSongViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JTextField searchField;
    private final JButton searchButton;
    private final JPopupMenu dropdownMenu = new JPopupMenu();

    public HomeView(LoggedInViewModel loggedInViewModel,
                    SearchViewModel searchViewModel,
                    SearchController searchController,
                    ViewSongViewModel viewSongViewModel,
                    ViewManagerModel viewManagerModel) {

        this.loggedInViewModel = loggedInViewModel;
        this.searchViewModel = searchViewModel;
        this.searchController = searchController;
        this.viewSongViewModel = viewSongViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.searchViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // ===== Header =====
        JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        JButton profileButton = new JButton("My Profile");
        header.add(title, BorderLayout.WEST);
        header.add(profileButton, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // ===== Search bar =====
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        center.add(Box.createVerticalStrut(20));
        center.add(searchPanel);
        center.add(Box.createVerticalStrut(20));

        // Logo
        java.net.URL logoUrl = getClass().getResource("/images/IMG_1233.PNG");
        JLabel logoLabel;

        if (logoUrl != null) {
            ImageIcon originalIcon = new ImageIcon(logoUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    400, 400, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(scaledImage));
        } else {
            logoLabel = new JLabel("LOGO");
        }

        JPanel logoWrapper = new JPanel();
        logoWrapper.add(logoLabel);
        center.add(logoWrapper);

        add(center, BorderLayout.CENTER);

        // ==== Listeners ====

        // Search button click
        searchButton.addActionListener(e ->
                searchController.executeSearch(searchField.getText())
        );

        // Live search
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                triggerLiveSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                triggerLiveSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                triggerLiveSearch();
            }
        });
    }

    // ========== Search helpers ==========
    private void triggerLiveSearch() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            searchController.executeSearch(query);
        } else {
            dropdownMenu.setVisible(false);
        }
    }

    private void showDropdown(java.util.List<SearchOutputData.SongResult> results) {
        dropdownMenu.removeAll();

        for (SearchOutputData.SongResult r : results) {
            JMenuItem item = new JMenuItem(r.getName() + " — " + r.getArtist());

            item.addActionListener(e -> {
                dropdownMenu.setVisible(false);

                // Prepare view song state
                ViewSongState newState = new ViewSongState();
                newState.setSongId(r.getId());
                newState.setSongName(r.getName());
                newState.setArtist(r.getArtist());

                viewSongViewModel.setState(newState);
                viewSongViewModel.firePropertyChange();

                // Navigate to SongProfileView
                viewManagerModel.setState("song profile");
                viewManagerModel.firePropertyChange();
            });

            dropdownMenu.add(item);
        }

        dropdownMenu.show(searchField, 0, searchField.getHeight());
    }

    // ========== ViewModel Listener ==========
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (Objects.equals(evt.getPropertyName(), SearchViewModel.RESULTS_PROPERTY)) {
            var results = searchViewModel.getResults();

            if (results == null) {
                dropdownMenu.setVisible(false);
                return;
            }

            if (results.isEmpty()) {
              dropdownMenu.setVisible(false);
                JOptionPane.showMessageDialog(this, "No results found.");
                return;
            }
            showDropdown(results);
        }
    }
}
