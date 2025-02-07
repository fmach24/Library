package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.BookList;
import com.example.library.repository.BookRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminController {

    private LogInController LogInControllerParent;

//    public void setMainController(LogInController controler){
//        LogInControllerParent = controler;
//    }

    public AdminController(LogInController loginController) {
        LogInControllerParent = loginController;

    }

//    Book currentBook = new Book();
//    int currentId;

    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> isRentedColumn;
    @FXML
    private Button addBookButton;
    @FXML
    private CheckBox showBorrowedCheckBox;
    @FXML
    private TextField searchTextField;

    private BookRepository bookRepository;
    private BookList bookList;

//    private final ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException, IOException {

        idColumn.setCellValueFactory(cdf -> cdf.getValue().idProperty().asString());
        titleColumn.setCellValueFactory(cdf -> cdf.getValue().titleProperty());
        authorColumn.setCellValueFactory(cdf -> cdf.getValue().authorProperty());
        isRentedColumn.setCellValueFactory(cdf -> cdf.getValue().statusLabel());
        isRentedColumn.setCellFactory(factory -> new ColoredStatusTableCell("-fx-background-color: #F5F5F5; -fx-font-size: 16px; -fx-font-family: 'Arial';"));


        bookRepository = new BookRepository();
        bookList = new BookList(bookRepository);
        booksTable.setItems(bookList.getCurrentList());


        //Obsluga table view
        booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {


                //todo naprawic bug z usuwaniem ksiazki
//                currentBook = obs.getValue();
//                currentId = newSelection.id.get();
//                currentBook = (readBook(currentId));
//                System.out.println(readBook(currentId));



                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editbook-view.fxml"));
                fxmlLoader.setController(new EditBookController(this, obs.getValue(), bookList));

                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 500, 350);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                EditBookController ctrl = fxmlLoader.getController();
//                ctrl.setMainController(this);

                stage.setTitle("Edytuj książkę");
                stage.initStyle(StageStyle.UNIFIED);
                stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
                stage.setScene(scene);
                stage.show();

                Platform.runLater(() -> {
                    booksTable.getSelectionModel().clearSelection();
                });
//                booksTable.getSelectionModel().clearSelection();

            }
        });

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
    private void handleAddBookButton(){
//                System.out.println(newSelection.genre);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addbook-view.fxml"));
            fxmlLoader.setController(new AddBookController(this, bookList));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 500, 335);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            AddBookController ctrl = fxmlLoader.getController();
//            ctrl.setMainController(this);
            stage.setTitle("Dodaj książkę");
            stage.initStyle(StageStyle.UNIFIED);
            stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
            stage.setScene(scene);
            stage.show();

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



        Stage libraryStage = (Stage) addBookButton.getScene().getWindow();
        libraryStage.close();

        showAlert("Konto","Wylogowano z konta administratora");
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