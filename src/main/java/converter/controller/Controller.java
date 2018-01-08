package converter.controller;

import converter.beans.InputItem;
import converter.beans.OutputItem;
import converter.dialogs.DialogUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller {

    @FXML private TextField loadingPath;
    @FXML private TextField savingPath;

    private Stage primaryStage;

    @FXML
    private void chooseFile () {
        loadingPath.clear();
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file!= null) {
            List<File> files = Arrays.asList(file);
            printLog(files);
        }
    }

    @FXML
    private void chosePath() {
        savingPath.clear();
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(primaryStage);
        savingPath.setText(file.getAbsolutePath() + "\\result.csv");
    }

    @FXML
    private void convertFile() {
        if(loadingPath.getText().length() == 0 || savingPath.getText().length() == 0 || loadingPath.getText().equals("") || savingPath.getText().equals("")) {
            converter.dialogs.DialogUtils.dialogCheckFilePaths();
        }else {
            if(!checkFile(loadingPath.getText()) || !checkFile(savingPath.getText())) {
                converter.dialogs.DialogUtils.dialogCheckIfCSV();
            }else {
                process();
                converter.dialogs.DialogUtils.dialogFileConverted();
            }
        }
    }

    @FXML
    public void closeApplication() {
        Optional<ButtonType> result = DialogUtils.confirmationDialogForCloseApp();
        if(result.get()==ButtonType.OK){
            Platform.exit();
            System.exit(0);
        }
    }

    private void printLog(List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            loadingPath.appendText(file.getAbsolutePath());
        }
    }

    private void process() {
        converter.service.CSVItemsReader reader = new converter.service.CSVItemsReader();
        String pathL = convertPath(loadingPath.getText());
        List<InputItem> inputItems = reader.loadCSVItems(pathL);

        converter.mapper.ItemMapper itemMapper = new converter.mapper.ItemMapper();
        List<OutputItem> outputItems = itemMapper.mapIntoOutputItemList(inputItems);

        converter.service.CSVItemsWriter writer = new converter.service.CSVItemsWriter();
        String pathS = convertPath(savingPath.getText());
        writer.writeDataToCSVFile(outputItems, pathS);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private String convertPath(String path) {
        String[] splitPath = path.split("\"");
        int elements = splitPath.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<elements; i++) {
            sb.append(splitPath[i]);
            if(i != elements - 1) {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    private boolean checkFile(String path) {
        return path.contains(".csv");
    }
}
