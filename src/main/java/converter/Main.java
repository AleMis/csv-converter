package converter;

import converter.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String windowFxml = "/scheme/window.fxml";
        try {

            Stage stage = new Stage();
            Scene scene = new Scene(new StackPane());

            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowFxml));
            scene.setRoot((Parent) loader.load());
            stage.setScene(scene);
            stage.setTitle("CSV Coverter");
            stage.show();
            Controller controller = loader.<Controller>getController();
            controller.setPrimaryStage(stage);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
