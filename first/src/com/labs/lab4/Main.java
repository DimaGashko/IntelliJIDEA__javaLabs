package com.labs.lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    private Stage window;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initView(primaryStage);

    }

    private void initView(Stage window) throws Exception {
        setWindow(window);
        window.setTitle("F2");

        Parent root = FXMLLoader.load(getClass().getResource("views/index.fxml"));
        scene = new Scene(root, 420, 660);

        window.setMinWidth(420);
        window.setMinHeight(400);

        window.setScene(scene);
        window.show();
    }

    private void setWindow(Stage window) {
        window = window;
    }
}
