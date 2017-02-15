package com.matrixprogramming.view;
/**
 * Created by Eldridge on 1/18/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application
{

    Stage window, welcomeScene, discoverScene, discoverResultScene;
    ImageView imageView;
    StackPane frame;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Image image = new Image("https://image.tmdb.org/t/p/w342/us4HARgUkkFluMIi7rOklKB0CJ5.jpg");
        imageView = new ImageView(image);
        Button btn = new Button("Click Me");
        btn.setOnAction(e -> btn_click());

        frame = new StackPane();

        frame.getChildren().addAll(imageView, btn);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Movie App");
        primaryStage.show();
        ListView<String> listView = new ListView<>();

    }

    public void btn_click()
    {
        //frame.getChildren().remove(imageView);
        imageView.setImage(new Image("http://www.gettyimages.com/gi-resources/images/Embed/new/embed2.jpg"));
        //frame.getChildren().add(imageView);
    }

}
