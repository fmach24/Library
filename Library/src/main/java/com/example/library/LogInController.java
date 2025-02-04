package com.example.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Błąd","Podaj nazwę użytkownika i hasło");
            return;
        }
        if (username.equals("admin") && password.equals("admin")) {


            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
            fxmlLoader.setController(new AdminController(this));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 900, 600);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            LibraryController ctrl = fxmlLoader.getController();
//            ctrl.setMainController(this);     niepotrzebny tutaj parent
            stage.setTitle("Administracja");
            stage.initStyle(StageStyle.UNIFIED);
            stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
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
        else if (password.equals("student")) {
//            Stage stage = new Stage();
//            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("library-view.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 700, 400);
//            stage.setTitle("Library");
//            stage.setScene(scene);
//            LibraryController ctrl = fxmlLoader.getController();
//            ctrl.setMainController(this);


            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("library-view.fxml"));
            fxmlLoader.setController(new LibraryController(this, username));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 900, 600);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            LibraryController ctrl = fxmlLoader.getController();
//            ctrl.setMainController(this);
            stage.setTitle("Biblioteka Knyszyn");
            stage.initStyle(StageStyle.UNIFIED);
            stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
            stage.setScene(scene);
            stage.show();

            //closing login stage
            Stage logInStage = (Stage) logInButton.getScene().getWindow();
            logInStage.close();
        }
        else  showAlert("Błąd","Niepoprawne dane");
    }

    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNIFIED);
        stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
        alert.showAndWait();
    }
}