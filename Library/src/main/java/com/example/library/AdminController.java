package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.BookList;
import com.example.library.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminController {

    private LogInController LogInControllerParent;

    public void setMainController(LogInController controler){
        LogInControllerParent = controler;
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
    private TableColumn<Book, String> amountColumn;
    private BookRepository bookRepository;
    private BookList bookList;

//    private final ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException, IOException {

        idColumn.setCellValueFactory(cdf -> cdf.getValue().idProperty().asString());
        titleColumn.setCellValueFactory(cdf -> cdf.getValue().titleProperty());
        authorColumn.setCellValueFactory(cdf -> cdf.getValue().authorProperty());


        bookRepository = new BookRepository();
        bookList = new BookList(bookRepository);
        booksTable.setItems(bookList.getCurrentList());


        //Obsluga table view
        booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

//                currentBook = obs.getValue();
//                currentId = newSelection.id.get();
//                currentBook = (readBook(currentId));
//                System.out.println(readBook(currentId));



                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editbook-view.fxml"));
                fxmlLoader.setController(new EditBookController(this, obs.getValue(), bookList));

                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                EditBookController ctrl = fxmlLoader.getController();
//                ctrl.setMainController(this);
                stage.setTitle("Edytuj książkę");
                stage.setScene(scene);
                stage.show();
            }
        });






    }
    @FXML
    private void handleAddBookButton(){
//                System.out.println(newSelection.genre);
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addbook-view.fxml"));
            fxmlLoader.setController(new AddBookController(this, bookList));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            AddBookController ctrl = fxmlLoader.getController();
//            ctrl.setMainController(this);
            stage.setTitle("Dodaj książkę");
            stage.setScene(scene);
            stage.show();

    }
//
//    public void addBook(Book book){
//        bookList.addBook(book);
//    }
//    public void updateBook(Book book){
//        bookList.updateBook(book);
//    }
//    public void deleteBook(Book book){
//        bookList.removeBook(book);
//    }
//    public Book readBook(int id){
//        return bookList.readBook(id);
//    }















//    @FXML
//    private TableView<Book> booksTable;
//    @FXML
//    private TableColumn<Book, Integer> idColumn;
//    @FXML
//    private TableColumn<Book, String> titleColumn;
//    @FXML
//    private TableColumn<Book, String> authorColumn;
//    @FXML
//    private TextField titleField;
//    @FXML
//    private TextField authorField;
//    @FXML
//    private Button addBookButton;
//    @FXML
//    private Button deleteBookButton;
//    @FXML
//    private TextField titleField;
//    @FXML
//    private TextField authorField;
//    @FXML
//    private TextField genreField;
//    @FXML
//    private TextField lengthField;
//
//    private final ObservableList<Book> books = FXCollections.observableArrayList();
//    private int nextId = 1;
//
//    @FXML
//    public void initialize() {
//        // Powiązanie kolumn z właściwościami klasy Book
//        idColumn.setCellValueFactory(data -> data.getValue().idProperty().asObject());
//        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
//        authorColumn.setCellValueFactory(data -> data.getValue().authorProperty());
//
//        // Przypisanie listy książek do tabeli
//        booksTable.setItems(books);
//
//        // Obsługa dodawania książek
//        addBookButton.setOnAction(event -> addBook());
//
//        // Obsługa usuwania książek
//        deleteBookButton.setOnAction(event -> deleteSelectedBook());
//    }
//
//    @FXML
//    private void addBook() {
//        String title = titleField.getText();
//        String author = authorField.getText();
//        String genre = genreField.getText();
//        String lenght = lenghtField.getText();
//
//        if (title.isEmpty() || author.isEmpty()) {
//            showAlert("Błąd", "Tytuł i autor nie mogą być puste!");
//            return;
//        }
//
//        books.add(new Book(nextId++, title, author, genre, Integer.parseInt(lenght)));
//        titleField.clear();
//        authorField.clear();
//    }
//
//    @FXML
//    private void deleteSelectedBook() {
//        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
//        if (selectedBook != null) {
//            books.remove(selectedBook);
//        } else {
//            showAlert("Błąd", "Wybierz książkę do usunięcia.");
//        }
//    }
//
//    @FXML
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    @FXML
//    private void refresh(){
//
//    }
////             car.addListener(this);
//
//    public static void main(String[] args) {
//        var url = "jdbc:sqlite:my.db";
//
//        // SQL statement for creating a new table
////        var sql = "DELETE FROM WAREHOUSES WHERE NAME = 'test1'";
//        var sql = "INSERT INTO WAREHOUSES(name,capacity) VALUES('test1', 2*10)";
//        //     "INSERT INTO WAREHOUSES(name,capacity) VALUES('test1', 2*10)"
//
//        try (var conn = DriverManager.getConnection(url);
//             var stmt = conn.createStatement()) {
//            // create a new table
//            stmt.execute(sql);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}