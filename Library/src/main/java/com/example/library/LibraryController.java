package com.example.library;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LibraryController {
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private Button addBookButton;
    @FXML
    private Button deleteBookButton;

    private final ObservableList<Book> books = FXCollections.observableArrayList();
    private int nextId = 1;

    @FXML
    public void initialize() {
        // Powiązanie kolumn z właściwościami klasy Book
        idColumn.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
        authorColumn.setCellValueFactory(data -> data.getValue().authorProperty());

        // Przypisanie listy książek do tabeli
        booksTable.setItems(books);

        // Obsługa dodawania książek
        addBookButton.setOnAction(event -> addBook());

        // Obsługa usuwania książek
        deleteBookButton.setOnAction(event -> deleteSelectedBook());
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();

        if (title.isEmpty() || author.isEmpty()) {
            showAlert("Błąd", "Tytuł i autor nie mogą być puste!");
            return;
        }

        books.add(new Book(nextId++, title, author));
        titleField.clear();
        authorField.clear();
    }

    private void deleteSelectedBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            books.remove(selectedBook);
        } else {
            showAlert("Błąd", "Wybierz książkę do usunięcia.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}