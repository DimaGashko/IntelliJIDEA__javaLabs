package com.labs.lab5.ELib.windows;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.controllers.Index;
import com.labs.lab5.ELib.models.storage.BinaryStorage;
import com.labs.lab5.ELib.models.storage.IStorage;
import com.labs.lab5.ELib.models.storage.TextStorage;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class IndexWindow extends BaseWindow<Index> {

    // Путь к файлу в котором хранятся книги
    static final private String DB_URL = "src/com/labs/lab5/ELib/configs/books-db.txt";
    static final private String DB_BIN_URL = "src/com/labs/lab5/ELib/configs/books-db";

    // Хранилице книг - содежит все книги
    private IStorage<Book> storage;

    private Alerts alerts = new Alerts();

    private IndexWindow() throws IOException {

    }

    public IndexWindow(Stage window) throws IOException {
        super(window);
    }

    protected void load() throws IOException {
        initStorage();

        var loader = new FXMLLoader(getClass().getResource("../views/index.fxml"));
        loader.setControllerFactory(this::controllerFactory);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        controller = loader.getController();

        window.setScene(scene);

        window.setTitle("ELib - your world of books");

        Image icon = new Image(getClass().getResource("../img/icon.png").toString());
        window.getIcons().add(icon);

        window.setMinWidth(700);
        window.setMinHeight(400);

        window.setMaximized(true);
    }

    private Index controllerFactory(Class<?> type) {
        Index index = new Index(storage);
        index.setOnExit(this::onExit);

        return index;
    }

    private void onExit() {
        window.close();
        Platform.exit();
        System.exit(0);
    }

    private void initStorage() {
        if (storage != null) return;

        try {
            //storage = new BinaryStorage<>(DB_BIN_URL, Book.class);
            storage = new TextStorage<>(DB_URL, Book::toString, Book::parse, Book.class);

        } catch (IOException err) {
            System.err.println(getStackTrace(err));
            //Обработка ошибки происходит в Index.initialize
        }

    }

    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }

}
