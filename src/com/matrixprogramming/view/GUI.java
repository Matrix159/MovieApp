package com.matrixprogramming.view;
/**
 * Created by Eldridge on 1/18/2017.
 */

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

public class GUI extends Application
{

    Stage window, welcomeScene, discoverScene, discoverResultScene;
    ImageView imageView;
    StackPane frame;
    MovieAPI movieAPI = new MovieAPI();
    ObservableList<HBox> items;
    ListView<HBox> listView;
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
        listView = (ListView<HBox>) root.lookup("#myList");
        Button discoverButton = (Button) root.lookup("#discoverButton");
        discoverButton.setOnAction((e) ->
        {
            pageCounter = 1;
            pageTotal = 0;
            items.clear();
            discover("popularity.desc", 1, pageTotal);

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

    public void discover(String sortBy, int pageCounter, final int pageTotal)
    {
        movieAPI.controller.discover("en-us", sortBy, "3|2", "US", true, pageCounter, "2017-02-02", "2017-03-02").enqueue(
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
