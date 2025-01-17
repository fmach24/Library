package com.example.library;

import com.example.library.models.Book;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.sql.DriverManager;
import java.sql.SQLException;

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
    private TextField genreField;
    @FXML
    private TextField lengthField;
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

    @FXML
    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        String length = lengthField.getText();

        if (title.isEmpty() || author.isEmpty()) {
            showAlert("Błąd", "Tytuł i autor nie mogą być puste!");
            return;
        }

        books.add(new Book(nextId++, title, author, genre, Integer.parseInt(length)));
        titleField.clear();
        authorField.clear();
    }

    @FXML
    private void deleteSelectedBook() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            books.remove(selectedBook);
        } else {
            showAlert("Błąd", "Wybierz książkę do usunięcia.");
        }
    }

    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void refresh(){

    }
//             car.addListener(this);

    public void dataBase() {
        var url = "jdbc:sqlite:my.db";

        // SQL statement for creating a new table
//        var sql = "DELETE FROM WAREHOUSES WHERE NAME = 'test1'";
        var sql = "INSERT INTO WAREHOUSES(name,capacity) VALUES('test1', 2*10)";
        //     "INSERT INTO WAREHOUSES(name,capacity) VALUES('test1', 2*10)"

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}