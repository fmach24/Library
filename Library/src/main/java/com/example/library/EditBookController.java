package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.BookList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditBookController {

    private AdminController AdminControllerParent;
    private final Book currentBook;
    private final BookList bookList;
//
//        public void setMainController(AdminController controler){
//            AdminControllerParent = controler;
//        }

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField publisherTextField;
    @FXML
    private Button deleteButton;
    @FXML
    private CheckBox isRentedCheckBox;



    public EditBookController(AdminController adminController, Book currentBook, BookList bookList) {
        AdminControllerParent = adminController;
        this.currentBook = currentBook;
        this.bookList = bookList;
    }


    @FXML
    public void initialize(){
//        id = AdminControllerParent.currentId;
//
//        currentBook = AdminControllerParent.currentBook;

        titleTextField.setPromptText(currentBook.title.get());
        authorTextField.setPromptText(currentBook.author.get());
        genreTextField.setPromptText(currentBook.genre.get());
        publisherTextField.setPromptText(currentBook.publisher.get());
    }


    @FXML
    private void handleConfirmButton() {
        String title = titleTextField.getText();
        String author = authorTextField.getText();
        String genre = genreTextField.getText();
        String publisher = publisherTextField.getText();
//        Book newBook = new Book(currentBook.id.get(), title, author, genre, publisher, false);
//
//        AdminControllerParent.updateBook(newBook);
        currentBook.titleProperty().set(title);
        currentBook.authorProperty().set(author);
        currentBook.genreProperty().set(genre);
        currentBook.publisherProperty().set(publisher);

        bookList.updateBook(currentBook);

        Stage addBookStage = (Stage) deleteButton.getScene().getWindow();
        addBookStage.close();
    }
    @FXML
    private void handleDeleteButton() {
//        AdminControllerParent.deleteBook(currentBook);
        bookList.removeBook(currentBook);

        Stage addBookStage = (Stage) deleteButton.getScene().getWindow();
        addBookStage.close();

    }

}
