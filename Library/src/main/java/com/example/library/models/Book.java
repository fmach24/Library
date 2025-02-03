package com.example.library.models;

import javafx.beans.property.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

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
            System.out.print("Książka jest wypożyczona, zwolni się " + this.expiration.get().toString());
            return;
        }
        this.isRented.set(true);
        this.expiration =  new SimpleObjectProperty<>(LocalDateTime.now().plusMinutes(1));
    }

    public void SetReturn(){
        if(!this.isRented.get()){
            System.out.print("Książka została zwrócona");
            return;
        }
        this.isRented.set(false);
        this.expiration.set(null);
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
}
