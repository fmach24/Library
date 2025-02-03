package com.example.library.models;

import javafx.beans.property.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Book {
    public IntegerProperty id;
    public StringProperty title;
    public StringProperty author;
    public StringProperty genre;
    public StringProperty publisher;
    public BooleanProperty isRented;
    public ObjectProperty<LocalDateTime> expiration;

    public Book(int id, String title, String author, String genre, String publisher, boolean isRented, LocalDateTime expiration) {

        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.publisher = new SimpleStringProperty(publisher);
        this.isRented = new SimpleBooleanProperty(isRented);
        this.expiration = new SimpleObjectProperty<>(expiration);
    }
    public Book(String title, String author, String genre,  String publisher, boolean isRented) {

        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.publisher = new SimpleStringProperty(publisher);
        this.isRented = new SimpleBooleanProperty(isRented);
    }
    public Book(int id, String title, String author, String genre,  String publisher, boolean isRented) {

        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.publisher = new SimpleStringProperty(publisher);
        this.isRented = new SimpleBooleanProperty(isRented);
    }
    public Book(){
    }

//    public String getTitle() {
//        return title.get();
//    }
//
//    public String getAuthor() {
//        return author.get();
//    }
//
//    public String getGenre() {
//        return genre.get();
//    }
//
//    public int getAmount() {
//        return amount.get();
//    }


    public void SetBorrow(){
        if(this.isRented.get()){
            showAlert("Książka", "Książka jest wypożyczona");
            return;
        }
        this.isRented.set(true);
        this.expiration =  new SimpleObjectProperty<>(LocalDateTime.now().plusDays(10));
        showAlert("Książka", "Książka została wypożyczona do "+this.expiration.get().toLocalDate().toString());
    }

    public void SetReturn(){
        if(!this.isRented.get()){
//            System.out.print("Książka została zwrócona");
            showAlert("Książka", "Książka jest już zwrócona");
            return;
        }
        this.isRented.set(false);
        this.expiration.set(null);
        showAlert("Książka", "Zwrócono książkę");
        //todo NO TUTAJ I W EXTEND SIE PSUJE COS
    }

    public void Extend(){
        if(!this.isRented.get()){
//            System.out.print("Książka została zwrócona");
            showAlert("Książka", "Książka została zwrócona");
            return;
        }
        this.expiration =  new SimpleObjectProperty<>(this.expiration.get().plusDays(1));
        showAlert("Książka", "Książka została przedłużona do "+this.expiration.get().toLocalDate().toString());
    }



    public IntegerProperty idProperty() {
    return id;
}
    public StringProperty titleProperty() {
        return title;
    }
    public StringProperty authorProperty() {
        return author;
    }
    public StringProperty genreProperty() {
        return genre;
    }
    public StringProperty publisherProperty() {
        return publisher;
    }

    @FXML
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
