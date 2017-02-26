package com.matrixprogramming.view;

import javafx.fxml.FXML;
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
    @FXML
    public ImageView posterImage;
    @FXML
    public Text movieTitleText;
    @FXML
    public Label voteAverageLabel;
    @FXML
    public Text movieDescription;
    @FXML
    public Text movieReleaseDate;
    @FXML
    public ImageView starIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
