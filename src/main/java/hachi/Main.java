package hachi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {
    private static Stage primaryStage;

    // Default save directory under user home
    private static final Path BASE_DIR = Paths.get(System.getProperty("user.home"), "hachi");
    private static final String TASK_FILE = "task.properties";
    private static final String NOTE_FILE = "note.properties";

    private final Hachi hachi =
            new Hachi(BASE_DIR.resolve(TASK_FILE).toString(),
                    BASE_DIR.resolve(NOTE_FILE).toString());

    @Override
    public void start(Stage stage) {
        try {
            // Ensure save folder exists
            File saveDir = BASE_DIR.toFile();
            if (!saveDir.exists() && !saveDir.mkdirs()) {
                throw new RuntimeException("Could not create save directory: " + saveDir);
            }

            primaryStage = stage;

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            scene.getStylesheets().add(
                    getClass().getResource("/view/dialog.css").toExternalForm()
            );

            // Inject Hachi into the controller
            MainWindow controller = fxmlLoader.getController();
            controller.setHachi(hachi);

            stage.setScene(scene);
            stage.setTitle("Hachi"); // optional: set window title
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


