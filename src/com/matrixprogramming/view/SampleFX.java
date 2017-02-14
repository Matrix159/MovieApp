package com.matrixprogramming.view; /**
 * Created by Eldridge on 1/18/2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SampleFX extends Application
{

    public static void main(String[] args)
    {
        //launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Button btn = new Button("Click Me");

        btn.setOnAction(e -> btn_click());

        StackPane frame = new StackPane();

        frame.getChildren().add(btn);

        Scene scene = new Scene(frame, 800, 600);

        primaryStage.setScene(scene);

        primaryStage.setTitle("No");
        primaryStage.show();
    }

    public void btn_click()
    {
        System.out.println("You Clicked th button");
    }

}
