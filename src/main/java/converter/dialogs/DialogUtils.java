package converter.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogUtils {

    public static void dialogCheckFilePaths() {
        Alert informationAlert = new Alert(Alert.AlertType.ERROR);
        informationAlert.setTitle("No path!");
        informationAlert.setHeaderText("Please, check the file paths!");
        informationAlert.setContentText("All text fields have to be filled!");
        informationAlert.showAndWait();
    }

    public static void dialogCheckIfCSV() {
        Alert informationAlert = new Alert(Alert.AlertType.ERROR);
        informationAlert.setTitle("Incorrect path!");
        informationAlert.setHeaderText("Please, check the file paths!");
        informationAlert.setContentText("Provided file is not .csv or final file name does not contain .csv!");
        informationAlert.showAndWait();
    }

    public static void dialogFileConverted() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("File converting");
        informationAlert.setHeaderText("File was successfully converted!");
        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationDialogForCloseApp() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Closing application");
        confirmationAlert.setHeaderText("Do you want to close csv converter?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        return result;
    }
}
