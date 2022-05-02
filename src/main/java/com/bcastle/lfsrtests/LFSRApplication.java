package com.bcastle.lfsrtests;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LFSRApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LFSRApplication.class.getResource("lfsr.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //IDE version
        stage.getIcons().add(new Image("/icon.png"));
        //Jar version
        //stage.getIcons().add(new Image( <yourclassname>.class.getResourceAsStream( "icon.png" )));
        stage.setTitle("LFSR Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}