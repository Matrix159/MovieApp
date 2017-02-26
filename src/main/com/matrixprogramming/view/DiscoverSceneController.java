package main.com.matrixprogramming.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Eldridge on 2/26/2017.
 */
public final class DiscoverSceneController implements Initializable {
    /** */
    @FXML
    private ComboBox sortByComboBox;
    /** */
    @FXML
    private Button discoverButton;
    /** */
    @FXML
    private ListView movieListView;

    /**
     * Method that runs when the controller is instantiated.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    /**
     * Returns the combo box with sorting options from fxml.
     * @return ComboBox
     */
    ComboBox getSortByComboBox() {
        return sortByComboBox;
    }

    /**
     * Returns the discover button from fxml.
     * @return Button
     */
    Button getDiscoverButton() {
        return discoverButton;
    }

    /**
     * Returns the movie list view from fxml.
     * @return ListView
     */
    ListView getMovieListView() {
        return movieListView;
    }
}
