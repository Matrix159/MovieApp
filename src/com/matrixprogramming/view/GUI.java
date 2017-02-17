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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;

/***
 * Created by Eldridge on 1/18/2017.
 */
public class GUI extends Application
{

    private Stage window, welcomeScene, discoverScene, discoverResultScene;
    private ImageView imageView;
    private ComboBox<String> sortByComboBox;
    StackPane frame;
    private MovieAPI movieAPI = new MovieAPI();
    private ObservableList<HBox> items;
    private ListView<HBox> listView;
    private static int pageCounter = 1;
    private static int pageTotal = 0;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Image image = new Image("https://image.tmdb.org/t/p/w154/us4HARgUkkFluMIi7rOklKB0CJ5.jpg");
        imageView = new ImageView(image);
        Image image1 = new Image("https://image.tmdb.org/t/p/w154/us4HARgUkkFluMIi7rOklKB0CJ5.jpg");
        ImageView imageView1 = new ImageView(image1);
        HBox hBox = new HBox();
        Label label = new Label("Test");
        hBox.getChildren().addAll(imageView, label);
        HBox hBox1 = new HBox();
        Label label1 = new Label("asdasd");
        hBox1.getChildren().addAll(imageView1, label1);
        HashMap<String, String> sortByMap = new HashMap<>();
        sortByMap.put("Popularity Ascending", "popularity.asc");
        sortByMap.put("Popularity Descending", "popularity.desc");
        sortByMap.put("Release Date Ascending", "release_date.asc");
        sortByMap.put("Release Date Descending", "release_date.desc");
        sortByMap.put("Revenue Ascending", "revenue.asc");
        sortByMap.put("Revenue Descending", "revenue.desc");
        sortByMap.put("Vote Average Ascending", "vote_average.asc");
        sortByMap.put("Vote Average Descending", "vote_average.desc");
        sortByMap.put("Vote Count Ascending", "vote_count.asc");
        sortByMap.put("Vote Count Descending", "vote_count.desc");
        listView = (ListView<HBox>) root.lookup("#myList");
        sortByComboBox = (ComboBox<String>) root.lookup("#sortByComboBox");
        sortByComboBox.getItems().addAll("Popularity Ascending", "Popularity Descending", "Release Date Ascending",
                "Release Date Descending", "Revenue Ascending", "Revenue Descending", "Vote Average Ascending",
                "Vote Average Descending", "Vote Count Ascending", "Vote Count Descending");
        Button discoverButton = (Button) root.lookup("#discoverButton");
        discoverButton.setOnAction((e) ->
        {
            pageCounter = 1;
            pageTotal = 0;
            items.clear();
            discover(sortByMap.get(sortByComboBox.getValue()), 1, pageTotal);

        });
        items = FXCollections.observableArrayList();
        listView.setItems(items);
        Scene scene = new Scene(root);
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Movie App");
        window.show();
    }

    /***
     * Button to allow the user to add a new movie
     * @param posterPath Path where the movie is to be placed
     * @param title Title of the movie
     */
    public void addMovie(String posterPath, String title)
    {
        Platform.runLater(() ->
        {
            HBox hBox = new HBox();
            Image image = new Image("https://image.tmdb.org/t/p/w154" + posterPath);
            ImageView imageView = new ImageView(image);
            Label label = new Label(title);
            hBox.getChildren().addAll(imageView, label);
            items.add(hBox);
        });
    }

    /***
     * Allows the user to search much more extensively for movies
     * @param sortBy String that we need the movies to be sorted by
     * @param pageCounter Int of the current movie page we're on
     * @param pageTotal Int that is the total number of pages
     */
    public void discover(String sortBy, int pageCounter, final int pageTotal)
    {
        movieAPI.controller.discover("en-us", sortBy, "3|2", "US", "en",
                true, pageCounter, "2017-02-02", "2017-03-02").enqueue(
                new Callback<DiscoverModel>()
                {
                    @Override
                    public void onResponse(Call<DiscoverModel> call, Response<DiscoverModel> response)
                    {
                        if (response.isSuccessful())
                        {
                            GUI.pageTotal = response.body().getTotalPages();
                            for (Result result : response.body().getResults())
                            {
                                addMovie(result.getPosterPath(), result.getTitle());
                            }

                            //System.out.println(GUI.pageTotal);
                            if (GUI.pageCounter < GUI.pageTotal)
                            {
                                GUI.pageCounter += 1;
                                discover("popularity.desc", GUI.pageCounter, GUI.pageTotal);

                            } else
                            {
                                Platform.runLater(() -> listView.setItems(items));
                            }

                        } else
                        {
                            try
                            {
                                System.out.println("Error: " + response.errorBody().string());
                            } catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DiscoverModel> call, Throwable t)
                    {
                        System.out.println("Retrofit call failed.");
                    }
                }
        );
    }

}
