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
    private Button cancelButton;


    public BookController(LibraryController libraryController, Book currentBook, BookList bookList) {

        LibraryControllerParent = libraryController;
        this.currentBook = currentBook;
        this.bookList = bookList;
    }

    @FXML
    private void handleBorrowButton(){
        currentBook.SetBorrow();
        bookList.updateBorrow(currentBook);


        System.out.println(currentBook.isRented.get());
        System.out.println(currentBook.expiration.get());

        Stage addBookStage = (Stage) cancelButton.getScene().getWindow();
        addBookStage.close();

    }

    @FXML
    private void handleExtendButton(){
        Stage addBookStage = (Stage) cancelButton.getScene().getWindow();
        addBookStage.close();

    }
    @FXML
    private void handleReturnButton(){
        currentBook.SetReturn();
        bookList.updateBorrow(currentBook);


        System.out.println(currentBook.isRented.get());
        System.out.println(currentBook.expiration.get());


        Stage addBookStage = (Stage) cancelButton.getScene().getWindow();
        addBookStage.close();

    }

    @FXML
    private void handleCancelButton(){
        Stage addBookStage = (Stage) cancelButton.getScene().getWindow();
        addBookStage.close();

    }


}
