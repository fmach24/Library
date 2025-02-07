package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.BookList;
import com.example.library.repository.BookRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class LibraryController {

    private LogInController LogInControllerParent;
    private final String username;

    public void setMainController(LogInController controler){
        LogInControllerParent = controler;
    }

    public LibraryController(LogInController loginController, String username) {
        LogInControllerParent = loginController;
        this.username = username;

    }


    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, String> isRentedColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private CheckBox showBorrowedCheckBox;
    @FXML
    private Label loggedAsText;

    private BookRepository bookRepository;
    private BookList bookList;

//    private final ObservableList<Book> books = FXCollections.observableArrayList();


    @FXML
    public void initialize() throws IOException, IOException{

        loggedAsText.setText("Użytkownik: " + username);

        titleColumn.setCellValueFactory(cdf -> cdf.getValue().titleProperty());
        authorColumn.setCellValueFactory(cdf -> cdf.getValue().authorProperty());
        genreColumn.setCellValueFactory(cdf -> cdf.getValue().genreProperty());
        isRentedColumn.setCellValueFactory(cdf -> cdf.getValue().statusLabel());
        isRentedColumn.setCellFactory(factory -> new ColoredStatusTableCell("-fx-background-color: #B692C2; -fx-font-size: 16px; -fx-font-family: 'Arial';"));

        //todo ustawic na autosize/fill ostatnia kolumne cos tego typu

//        System.out.println(username);


        //todo witaj *uzytkownik* !

        //Obsluga table view
        booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
//               System.out.println(newSelection.genre);
//                currentId = newSelection.id.get();
//                currentBook = readBook(currentId);

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("book-view.fxml"));
                fxmlLoader.setController(new BookController(this, obs.getValue(), bookList));

                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                BookController ctrl = fxmlLoader.getController();
//                ctrl.setMainController(this);
                stage.setTitle("Informacje o książce");
                stage.initStyle(StageStyle.UNIFIED);
                stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
                stage.setScene(scene);
                stage.show();

                Platform.runLater(() -> {
                    booksTable.getSelectionModel().clearSelection();
                });
            }
        });

        //stworzenie book repository
        bookRepository = new BookRepository();
        bookList = new BookList(bookRepository);
        booksTable.setItems(bookList.getCurrentList());
    }

    @FXML
    private void handleSearchButton(){
        String filter = searchTextField.getText();
        bookList.setFilter(filter);
    }

    @FXML
    private void handleShowBorrowedCheckBox(){
        if (showBorrowedCheckBox.isSelected()){
            bookList.onlyBorrow = true;
        }
        else {
            bookList.onlyBorrow = false;
        }
        bookList.readBooks();
    }

    @FXML
    private void handleLogOutButton(){

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("log-in-view.fxml"));
//        fxmlLoader.setController(new AdminController(this));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//            LibraryController ctrl = fxmlLoader.getController();
//            ctrl.setMainController(this);     niepotrzebny tutaj parent

        stage.setTitle("Log in");
        stage.initStyle(StageStyle.UNIFIED);
        stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
        stage.setScene(scene);
        stage.show();



        Stage libraryStage = (Stage) searchTextField.getScene().getWindow();
        libraryStage.close();

        showAlert("Konto","Wylogowano z konta " + username);
    }

    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNIFIED);
        stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
        alert.showAndWait();
    }
}