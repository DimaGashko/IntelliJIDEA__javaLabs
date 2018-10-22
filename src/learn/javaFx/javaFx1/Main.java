package learn.javaFx.javaFx1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {
    Button button;

    public static void main(String[] args) {
            Main.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My first a native JavaFx Application");

        button = new Button("Button");
        button.setOnAction(this);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setScene(scene);

        //primaryStage.setWidth(200);
        //primaryStage.setHeight(180);

        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button) {
            System.out.println("Button!");
        }
    }
}