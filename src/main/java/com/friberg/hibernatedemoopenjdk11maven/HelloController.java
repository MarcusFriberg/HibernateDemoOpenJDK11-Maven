package com.friberg.hibernatedemoopenjdk11maven;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Skriver ut lista på bilar");
        //HelloApplication.printCars();
    }
}