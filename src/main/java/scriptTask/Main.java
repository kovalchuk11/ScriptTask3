package scriptTask;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    String filePath;
    String saveFilePath;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button choseFile = new Button("Выбрать файл");
        Button saveFilePathBut = new Button("Выбрать место сохранения файла");
        Button solutionButton = new Button("Решить");
        Label chosen = new Label();
        Label savePathLabel = new Label();
        Label infoLabel = new Label();

        choseFile.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(primaryStage);

            if (file != null) {
                String fileAsString = file.toString();
                filePath = fileAsString;

                chosen.setText("Chosen: " + fileAsString);
            } else {
                chosen.setText(null);
            }
        });

        saveFilePathBut.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(primaryStage);
            if (file != null) {
                String fileAsString = file.toString();
                saveFilePath = fileAsString;

                savePathLabel.setText("Сохраним файл сюда: " + saveFilePath);
            } else {
                chosen.setText(null);
            }
        });

        solutionButton.setOnAction(event -> {
            if(saveFilePath == null){
                infoLabel.setText("Выберите куда сохранить файл");
            }

            if (filePath != null && saveFilePath != null){


                infoLabel.setText("Большие файлы обрабатываются несколько секунд");
                new ResolverThread(filePath, saveFilePath);
            }
        });

        VBox layout = new VBox(10, choseFile, chosen, saveFilePathBut, savePathLabel, solutionButton,  infoLabel);
        layout.setMinWidth(400);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        primaryStage.setScene(new Scene(layout));
        primaryStage.show();
    }
}
