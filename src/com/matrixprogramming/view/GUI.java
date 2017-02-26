package com.matrixprogramming.view;

import com.matrixprogramming.controller.MovieAPI;
import com.matrixprogramming.model.DiscoverModel;
import com.matrixprogramming.model.Result;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

/***
 * Created by Eldridge on 1/18/2017.
 */
public class GUI extends Application {
    //private Stage window, welcomeScene, discoverScene, discoverResultScene;
    //private ImageView imageView;

    /** window. **/
    private ComboBox<String> sortByComboBox;
    //StackPane frame;

    /** Create instance of the movie DB API. **/
    private MovieAPI movieAPI = new MovieAPI();
    /** Items. **/
    private ObservableList<HBox> items;

    /** ListView. **/
    private ListView<HBox> listView;

    /** PageCounter. **/
    private static int pageCounter = 1;

    /** PageTotal. **/
    private static int pageTotal = 0;

    /** Sorted map. **/
    private HashMap<String, String> sortByMap = new HashMap<>();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage window) throws IOException {
        final int windowWidth = 800;
        final int windowHeight = 600;

        Parent root = FXMLLoader.load(getClass().getResource("discover_scene.fxml"));
        Image image = new Image("https://image.tmdb.org/t/p/w154/us4HARgUkkFluMIi7rOklKB0CJ5.jpg");
        ImageView imageView = new ImageView(image);
        Image image1 = new Image("https://image.tmdb.org/t/p/w154/us4HARgUkkFluMIi7rOklKB0CJ5.jpg");
        ImageView imageView1 = new ImageView(image1);
        HBox hBox = new HBox();
        Label label = new Label("Test");
        hBox.getChildren().addAll(imageView, label);
        HBox hBox1 = new HBox();
        Label label1 = new Label("asdasd");
        hBox1.getChildren().addAll(imageView1, label1);
        sortByMapSetup(sortByMap);
        listView = (ListView<HBox>) root.lookup("#myList");
        sortByComboBox = (ComboBox<String>) root.lookup("#sortByComboBox");
        sortByComboBox.getItems().addAll("Popularity Ascending",
                "Popularity Descending", "Release Date Ascending",
                "Release Date Descending", "Revenue Ascending",
                "Revenue Descending", "Vote Average Ascending",
                "Vote Average Descending", "Vote Count Ascending",
                "Vote Count Descending");
        Button discoverButton = (Button) root.lookup("#discoverButton");
        discoverButton.setOnAction((e) -> {
            pageCounter = 1;
            pageTotal = 0;
            items.clear();
            discover(sortByMap.get(sortByComboBox.getValue()), 1);

        });
        items = FXCollections.observableArrayList();
        listView.setItems(items);
        Scene scene = new Scene(root);
        window.setWidth(windowWidth);
        window.setHeight(windowHeight);
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Movie App");
        window.show();
    }

    /***
     * Button to allow the user to add a new movie.
     * @param posterPath Path where the movie is to be placed
     * @param title Title of the movie
     * @param voteAverage Average rating of movie
     * @param overview Small description of movie
     * @param releaseDate Release date of movie
     */
    private void addMovie(final String posterPath,
                          final String title,
                          final double voteAverage,
                          final String overview,
                          final String releaseDate) {
        Platform.runLater(() -> {
            HBox root;
            MovieController movieController;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("movie_item.fxml"));
                root = (HBox) loader.load();
                movieController = loader.getController();

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            movieController.posterImage.setImage(new Image("https://image.tmdb.org/t/p/w154" + posterPath));
            movieController.movieTitleText.setText(title);
            movieController.voteAverageLabel.setText(String.valueOf(voteAverage));
            movieController.movieDescription.setText(overview);
            movieController.starIcon.setImage(new Image(getClass().getResourceAsStream("/star.png")));
            movieController.movieReleaseDate.setText(releaseDate);
            items.add(root);
        });
    }

    /*****
     * Allows the user to search much more extensively for movies.
     * @param sortBy String that we need the movies to be sorted by
     * @param pageCounter Int of the current movie page we're on
     ****/
    private void discover(final String sortBy, final int pageCounter) {

        LocalDate now = LocalDate.now();

        LocalDate twoWeeksAgo = now.minusWeeks(2);
        LocalDate twoWeeksAhead = now.plusWeeks(2);
        System.out.println(twoWeeksAgo);
        System.out.println(twoWeeksAhead);
        movieAPI.controller.discover("en-us", sortBy,
                "3|2", "US", "en", true,
                pageCounter, twoWeeksAgo.toString(),
                twoWeeksAhead.toString()).enqueue(
                        new Callback<DiscoverModel>() {
                    @Override
                    public void onResponse(final Call<DiscoverModel> call, final Response<DiscoverModel> response) {
                        if (response.isSuccessful()) {
                            GUI.pageTotal = response.body().getTotalPages();
                            for (Result result : response.body().getResults()) {
                                addMovie(result.getPosterPath(), result.getTitle(), result.getVoteAverage(), result.getOverview(), result.getReleaseDate());
                            }

                            //System.out.println(GUI.pageTotal);
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

    /**
     * Sets up the sortByMap for the sort by combobox.
     *
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
