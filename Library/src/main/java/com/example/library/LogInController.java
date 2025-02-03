package com.example.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LogInController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button logInButton;


    @FXML
    public void initialize(){
    }

    @FXML
    private void handleLogInButton() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username.isEmpty()) {
            showAlert("Błąd","Podaj nazwę użytkownika");
            return;
        }
        if (username.equals("admin") && password.equals("admin")) {


            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            LibraryController ctrl = fxmlLoader.getController();
//            ctrl.setMainController(this);     niepotrzebny tutaj parent
            stage.setTitle("Administracja");
            stage.setScene(scene);
            stage.show();

            //closing login stage
            Stage logInStage = (Stage) logInButton.getScene().getWindow();
            logInStage.close();



//
//            Stage stage = new Stage();
//            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 700, 400);
//            stage.setTitle("Admin");
//            stage.setScene(scene);
//            stage.show();
            //closing login stage
//            Stage logInStage = (Stage) logInButton.getScene().getWindow();
//            logInStage.close();




        }
        else{
//            Stage stage = new Stage();
//            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("library-view.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 700, 400);
//            stage.setTitle("Library");
//            stage.setScene(scene);
//            LibraryController ctrl = fxmlLoader.getController();
//            ctrl.setMainController(this);


            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("library-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            LibraryController ctrl = fxmlLoader.getController();
            ctrl.setMainController(this);
            stage.setTitle("Biblioteka");
            stage.setScene(scene);
            stage.show();

            //closing login stage
            Stage logInStage = (Stage) logInButton.getScene().getWindow();
            logInStage.close();

        }
    }

    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}