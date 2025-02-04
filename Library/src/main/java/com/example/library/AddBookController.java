package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.BookList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddBookController {

    private AdminController AdminControllerParent;
    private final BookList bookList;

    public void setMainController(AdminController controler){
        AdminControllerParent = controler;
    }

    public AddBookController(AdminController adminController, BookList bookList) {
        AdminControllerParent = adminController;
        this.bookList = bookList;
    }


    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField publisherTextField;
    @FXML
    private Button addButton;


    @FXML
    public void initialize(){

    }

    @FXML
    private void handleAddButton(){
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String publisher = publisherTextField.getText();

        if(title.isEmpty() || author.isEmpty() || genre.isEmpty() || publisher.isEmpty()) {
            showAlert("Książka", "Uzupełnij dane");
            return;
        }

        Book book = new Book(title, author, genre, publisher, false);
        bookList.addBook(book);

        showAlert("Książka", "Dodano książkę");

        Stage addBookStage = (Stage) addButton.getScene().getWindow();
        addBookStage.close();

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
