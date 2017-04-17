package main.com.matrixprogramming.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/***
 * Created by Eldridge on 2/19/2017.
 */
public class MovieController implements Initializable {
    /**
     * Button to contain the favorite action.
     */
    @FXML
    private Button favButton;
    /**
     * ImageView container for movie images.
     */
    @FXML
    private ImageView posterImage;
    /**
     * Text view for the movie title.
     */
    @FXML
    private Text movieTitleText;
    /**
     * Label to show vote average of movie.
     */
    @FXML
    private Label voteAverageLabel;
    /**
     * Text view for the movie description.
     */
    @FXML
    private Text movieDescription;
    /**
     * Text view for the movie release date.
     */
    @FXML
    private Text movieReleaseDate;
    /**
     * Image container for the vote star icon.
     */
    @FXML
    private ImageView starIcon;

    /** Keeps track if a movie is favorite or not. **/
    public boolean favorite = false;

    private String posterPath;

    /***
     * @param location Location.
     * @param resources Resources.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        favButton.setOnAction(event -> {
                    Button button = (Button) event.getSource();
                    if (!favorite) {
                        button.setGraphic(new ImageView(new Image("favoritedStar.png")));
                        GUI.saveMovie(getMovieTitleText().getText(), getPosterPath(),
                                getVoteAverageLabel().getText(),
                                getMovieDescription().getText(), getMovieReleaseDate().getText());
                        favorite = true;

                    } else {
                        button.setGraphic(new ImageView(new Image("favoriteStarOutline.png")));
                        GUI.deleteMovie(getMovieTitleText().getText(), getPosterPath(),
                                getVoteAverageLabel().getText(),
                                getMovieDescription().getText(), getMovieReleaseDate().getText());
                        favorite = false;
                    }
                }
        );
    }

    /**
     * Returns the poster image view.
     *
     * @return ImageView
     */
    public ImageView getPosterImage()
    {
        return posterImage;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public void setPosterPath(final String posterPath)
    {
        this.posterPath = posterPath;
    }
    /**
     * Returns the star image view.
     *
     * @return ImageView
     */
    public ImageView getStarIcon()
    {
        return starIcon;
    }

    /**
     * Returns the favorite button
     *
     * @return ButtonView
     */
    public Button getFavButton()
    {
        return favButton;
    }

    /**
     * Sets the image of the favorite button
     */
    public void setFavButton(String url) {
        favButton.setGraphic(new ImageView(new Image(url)));
    }


    /***
     * Returns title of movie
     * @return Text
     */
    public Text getMovieTitleText()
    {
        return movieTitleText;
    }

    /**
     * Returns the vote average label.
     *
     * @return Label
     */
    public Label getVoteAverageLabel()
    {
        return voteAverageLabel;
    }

    /**
     * Returns the movie description.
     *
     * @return Text
     */
    public Text getMovieDescription()
    {
        return movieDescription;
    }

    /**
     * Returns the movie release date.
     *
     * @return Text
     */
    public Text getMovieReleaseDate()
    {
        return movieReleaseDate;
    }




}
