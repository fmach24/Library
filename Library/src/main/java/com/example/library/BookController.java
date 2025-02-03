package com.example.library;


import com.example.library.models.Book;
import com.example.library.models.BookList;
import com.example.library.repository.BookRepository;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BookController {

    private LibraryController LibraryControllerParent;
    private final Book currentBook;
    private final BookList bookList;

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField publisherTextField;
    @FXML
    private TextField expirationTextField;
    @FXML
    private Button cancelButton;


    public BookController(LibraryController libraryController, Book currentBook, BookList bookList) {

        LibraryControllerParent = libraryController;
        this.currentBook = currentBook;
        this.bookList = bookList;
    }

    public void initialize() {
        titleTextField.setText(currentBook.title.get());
        authorTextField.setText(currentBook.author.get());
        genreTextField.setText(currentBook.genre.get());
        publisherTextField.setText(currentBook.publisher.get());
        //ustawia date oddania
        if(currentBook.isRented.get()) expirationTextField.setText(currentBook.expiration.get().toLocalDate().toString());
        else expirationTextField.setText(null);
    }

    @FXML
    private void handleBorrowButton(){
        currentBook.SetBorrow();
        bookList.updateBorrow(currentBook);
        initialize();
//        showAlert("Książka", "Książka została wypożyczona do " + currentBook.expiration.get().toLocalDate().toString());


//        System.out.println(currentBook.isRented.get());
//        System.out.println(currentBook.expiration.get());

//        Stage addBookStage = (Stage) cancelButton.getScene().getWindow();
//        addBookStage.close();

    }

    @FXML
    private void handleExtendButton(){
        currentBook.Extend();
        bookList.updateBorrow(currentBook);
        initialize();
//        showAlert("Książka", "Książka została przedłużona do " + currentBook.expiration.get().toLocalDate().toString());
//        Stage addBookStage = (Stage) cancelButton.getScene().getWindow();
//        addBookStage.close();

    }
    @FXML
    private void handleReturnButton(){
        currentBook.SetReturn();
        bookList.updateBorrow(currentBook);
        initialize();

//        showAlert("Książka", "Zwrócono książkę");
//        System.out.println(currentBook.isRented.get());
//        System.out.println(currentBook.expiration.get());


//        Stage addBookStage = (Stage) cancelButton.getScene().getWindow();
//        addBookStage.close();

    }

    @FXML
    private void handleCancelButton(){
        Stage addBookStage = (Stage) cancelButton.getScene().getWindow();
        addBookStage.close();

    }

//    @FXML
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }


}
