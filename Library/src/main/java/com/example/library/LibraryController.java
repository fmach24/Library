package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.BookList;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

public class LibraryController {

    private BookList bookList= new BookList();

    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> amountColumn;

//    private final ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Powiązanie kolumn z właściwościami klasy Book

        titleColumn.setCellValueFactory(cdf -> cdf.getValue().titleProperty());
        authorColumn.setCellValueFactory(cdf -> cdf.getValue().authorProperty());
        amountColumn.setCellValueFactory(cdf -> cdf.getValue().amountProperty());

        // Przypisanie listy książek do tabeli
//        booksTable.setItems(books);
        booksTable.setItems(bookList.getCurrentList());
    }
//    @FXML
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
}