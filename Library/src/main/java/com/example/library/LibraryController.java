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

public class LibraryController {





    @FXML
    private TableView<Book> booksTable;
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
        titleColumn.setCellValueFactory(cdf -> cdf.getValue().titleProperty());
        authorColumn.setCellValueFactory(cdf -> cdf.getValue().authorProperty());
//        amountColumn.setCellValueFactory(cdf -> cdf.getValue().amountProperty());


        //Obsluga table view
        booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
               System.out.println(newSelection.genre);
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("log-in-view.fxml"));//TODO dodac poprawny fxml
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                BookController ctrl = fxmlLoader.getController();
//                ctrl.setMainController(this);
                stage.setTitle("Informacje o książce");
                stage.setScene(scene);
                stage.show();
            }
        });


        bookRepository = new BookRepository();
        bookList = new BookList(bookRepository);
        booksTable.setItems(bookList.getCurrentList());



//        bookRepository.delete(bookList.getCurrentList().getFirst().id);
        Book book1 = new Book("miua","dss", "hgss", "fsd", false);
        bookList.addBook(book1);
//        bookList.remove(book1);

//        System.out.println(bookList.getCurrentList());


//        book1 = bookList.getCurrentList().get(0);
//        Book book1 = new Book(UUID.randomUUID(), "s", "s", "s", "2");
//
//        BookRepository.create(bookList.getCurrentList().get(0));
//        BookRepository.create(bookList.getCurrentList().get(1));
//        BookRepository.create(bookList.getCurrentList().get(2));
//        BookRepository.create(bookList.getCurrentList().get(3));
//        BookRepository.create(bookList.getCurrentList().get(4));
//        BookRepository.create(bookList.getCurrentList().get(5));





    }
//    @FXML
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
}