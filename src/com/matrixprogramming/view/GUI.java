package com.matrixprogramming.view;
/**
 * Created by Eldridge on 1/18/2017.
 */

import com.matrixprogramming.controller.MovieAPI;
import com.matrixprogramming.model.DiscoverModel;
import javafx.application.Application;
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
        ListView<HBox> listView = (ListView<HBox>) root.lookup("#myList");
        Button discoverButton = (Button) root.lookup("#discoverButton");
        discoverButton.setOnAction((e) -> discover("popularity.desc"));
        ObservableList<HBox> items = FXCollections.observableArrayList(hBox, hBox1);
        listView.setItems(items);
        Scene scene = new Scene(root);
        window.setWidth(800);
        window.setHeight(600);
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Movie App");
        window.show();
    }



    public void discover(String sortBy)
    {
        movieAPI.controller.discover("en-us", sortBy,"2|3", "US").enqueue(
                new Callback<DiscoverModel>()
                {
                    @Override
                    public void onResponse(Call<DiscoverModel> call, Response<DiscoverModel> response)
                    {
                        if(response.isSuccessful())
                        {
                            System.out.println(response.body().toString());
                        }
                        else
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
