package com.example.library.models;

import javafx.beans.property.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class Book {
    public UUID id;
    public StringProperty title;
    public StringProperty author;
    public StringProperty genre;
    public StringProperty amount;

    public Book(UUID id, String title, String author, String genre, String amount) {

        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.amount = new SimpleStringProperty(amount);
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
    public StringProperty amountProperty() {
        return amount;
    }
}
