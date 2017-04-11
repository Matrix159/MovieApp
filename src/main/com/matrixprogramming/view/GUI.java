package main.com.matrixprogramming.view;

import javafx.scene.control.MenuItem;
import main.com.matrixprogramming.controller.MovieAPI;
import main.com.matrixprogramming.model.DiscoverModel;
import main.com.matrixprogramming.model.Result;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/***
 * GUI class that displays the main program window and handles all user
 * actions.
 */
public final class GUI extends Application {


    /**
     * Combo box containing sorting options.
     */
    private ComboBox<String> sortByComboBox;


    /**
     * Create instance of the movie DB API.
     **/
    private MovieAPI movieAPI = new MovieAPI();
    /**
     * Items.
     **/
    private ObservableList<HBox> items;

    /**
     * ListView.
     **/
    private ListView<HBox> listView;

    /**
     * Discover button for GUI.
     */
    private Button discoverButton;
    /**
     * PageCounter.
     **/
    private static int pageCounter = 1;
    /**
     * Close button
     */
    private static MenuItem close;

    /**
     * PageTotal.
     **/
    private static int pageTotal = 0;

    /**
     * Sorted map.
     **/
    private HashMap<String, String> sortByMap = new HashMap<>();

    /**
     * Window height and width constants for main window.
     */
    private static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;

    /**
     * The main starting point of the program.
     * @param args Arguments from commandline
     */
    public static void main(final String[] args) {
        launch(args);

    }

    /**
     * Start method called upon launch(String[] args).
     * @param window The main stage
     * @throws IOException Throws
     */
    @Override
    public void start(final Stage window) throws IOException {
        // This should fix the touch screen bug.
        System.setProperty("glass.accessible.force", "false");
        sortByMapSetup(sortByMap);
        BorderPane root;
        DiscoverSceneController discoverSceneController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("discover_scene.fxml"));
        root = loader.load();
        discoverSceneController = loader.getController();
        close = discoverSceneController.getClose();
        listView = discoverSceneController.getMovieListView();
        sortByComboBox = discoverSceneController.getSortByComboBox();
        sortByComboBox.getItems().addAll("Popularity Ascending",
                "Popularity Descending", "Release Date Ascending",
                "Release Date Descending", "Revenue Ascending",
                "Revenue Descending", "Vote Average Ascending",
                "Vote Average Descending", "Vote Count Ascending",
                "Vote Count Descending");
        discoverButton = discoverSceneController.getDiscoverButton();
        discoverButton.setOnAction((e) -> {
            pageCounter = 1;
            pageTotal = 0;
            items.clear();
            discover(sortByMap.get(sortByComboBox.getValue()), 1);

        });
        items = FXCollections.observableArrayList();
        listView.setItems(items);
        Scene scene = new Scene(root);
        window.setWidth(WINDOW_WIDTH);
        window.setHeight(WINDOW_HEIGHT);
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Movie App");
        window.show();
        close.setOnAction((e) -> {
            System.exit(0);
        });
    }

    /***
     * Button to allow the user to add a new movie.
     * @param posterPath Path where the movie is to be placed
     * @param title Title of the movie
     * @param voteAverage Average rating of movie
     * @param overview Small description of movie
     * @param releaseDate Release date of movie
     */
    public void addMovie(final String posterPath,
                          final String title,
                          final double voteAverage,
                          final String overview,
                          final String releaseDate) {
        Platform.runLater(() -> {
            HBox root;
            MovieController movieController;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("movie_item.fxml"));
                root = loader.load();
                movieController = loader.getController();

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            movieController.getPosterImage().setImage(new Image("https://image.tmdb.org/t/p/w154" + posterPath));
            movieController.getMovieTitleText().setText(title);
            movieController.getVoteAverageLabel().setText(String.valueOf(voteAverage));
            movieController.getMovieDescription().setText(overview);
            movieController.getStarIcon().setImage(new Image(getClass().getResourceAsStream("/star.png")));
            movieController.getMovieReleaseDate().setText(releaseDate);
            //movieController.setFavButton(new Button("Favorite", new ImageView(new Image(getClass().getResourceAsStream("/favoriteStarOutlineSmall.png")))));
            movieController.setFavButton(new Button());
            movieController.setFavImage(new Image("favoriteStarOutline.png"));
            items.add(root);
        });

    }

    /*****
     * Allows the user to search for movies.
     * @param sortBy String that we need the movies to be sorted by
     * @param localPageCounter Int of the current movie page we're on
     ****/
    private void discover(final String sortBy, final int localPageCounter) {

        LocalDate now = LocalDate.now();

        LocalDate twoWeeksAgo = now.minusWeeks(2);
        LocalDate twoWeeksAhead = now.plusWeeks(2);

        // System.out.println(twoWeeksAgo);
        // System.out.println(twoWeeksAhead);
        movieAPI.getController().discover("en-us", sortBy,
                "3|2", "US", "en", true,
                localPageCounter, twoWeeksAgo.toString(),
                twoWeeksAhead.toString()).enqueue(
                new Callback<DiscoverModel>() {
                    @Override
                    public void onResponse(final Call<DiscoverModel> call, final Response<DiscoverModel> response) {
                        if (response.isSuccessful()) {
                            GUI.pageTotal = response.body().getTotalPages();
                            for (Result result : response.body().getResults()) {
                                addMovie(result.getPosterPath(), result.getTitle(), result.getVoteAverage(),
                                        result.getOverview(), result.getReleaseDate());
                            }
                            System.out.println("Does this keep running?");
                            if (GUI.pageCounter < GUI.pageTotal) {
                                GUI.pageCounter += 1;
                                discover(sortBy, GUI.pageCounter);

                            } else {
                                Platform.runLater(() -> listView.setItems(items));
                            }

                        } else {
                            try {
                                System.out.println("Error: " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(final Call<DiscoverModel> call,
                                          final Throwable t) {
                        System.out.println("Retrofit call failed.");
                    }
                }
        );
    }

    /***
     * Sets up the sortByMap for the sort by combobox.
     * @param sortMovieByMap The Hashmap to map sort by values for
     *                       discover scene
     */
    private void sortByMapSetup(final HashMap<String, String> sortMovieByMap) {
        sortMovieByMap.put("Popularity Ascending", "popularity.asc");
        sortMovieByMap.put("Popularity Descending", "popularity.desc");
        sortMovieByMap.put("Release Date Ascending", "release_date.asc");
        sortMovieByMap.put("Release Date Descending", "release_date.desc");
        sortMovieByMap.put("Revenue Ascending", "revenue.asc");
        sortMovieByMap.put("Revenue Descending", "revenue.desc");
        sortMovieByMap.put("Vote Average Ascending", "vote_average.asc");
        sortMovieByMap.put("Vote Average Descending", "vote_average.desc");
        sortMovieByMap.put("Vote Count Ascending", "vote_count.asc");
        sortMovieByMap.put("Vote Count Descending", "vote_count.desc");
    }



}
