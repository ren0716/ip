package hachi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Hachi hachi = new Hachi("output/output.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Load dialog.css
            scene.getStylesheets().add(
                    getClass().getResource("/view/dialog.css").toExternalForm()
            );

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHachi(hachi);  // inject bot
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

