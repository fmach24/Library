package com.example.library.models;

import javafx.beans.property.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Book {
    public int id;
    public StringProperty title;
    public StringProperty author;
    public StringProperty genre;
    public StringProperty publisher;
    public BooleanProperty isRented;
    public ObjectProperty<LocalDateTime> expiration;

    public Book(int id, String title, String author, String genre, String publisher, boolean isRented, LocalDateTime expiration) {

        this.id = id;
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

        this.id = id;
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
