package com.matrixprogramming.view;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Eldridge on 2/19/2017.
 */
public class MovieController implements Initializable
{
    public ImageView posterImage;
    public Text movieTitleText;
    public Label voteAverageLabel;
    public Text movieDescription;
    public Text movieReleaseDate;
    public ImageView starIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //System.out.println("Initialized a movie.");
    }
}
