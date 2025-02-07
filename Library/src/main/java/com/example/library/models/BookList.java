package com.example.library.models;

import com.example.library.Listener;
import com.example.library.repository.BookRepository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.collections.ListChangeListener;

public class BookList {

    private ObservableList<Book> books;
//    private ObservableList<Book> current;
    private BookRepository repository;

    public String currentFilter;
    public boolean onlyBorrow;

    public BookList(BookRepository repository) {

        this.repository = repository;

        books = FXCollections.observableArrayList(repository.read(null, false));
    }

    public ObservableList<Book> getCurrentList() {
        return books;
    }

    public void addBook(Book book) {
        int id = repository.create(book);
        book.id = new SimpleIntegerProperty(id);
        books.add(book);
    }

    public void updateBook(Book book) {
       repository.update(book);
       this.readBooks();
//       current.clear();
//       current.addAll(books);
    }

    public Book readBook(int id) {
        return repository.read(id);
    }

    public void readBooks() {
        books.clear();
        books.addAll(repository.read(currentFilter, onlyBorrow));
    }

    public void updateBorrow(Book book) {
        repository.updateBorrow(book);
        this.readBooks();
    }

    public void removeBook(Book book) {
        repository.delete(book.idProperty().get());
        books.remove(book);
    }

    public void setFilter(String filter) {

        this.currentFilter = filter.isEmpty() ? null : filter;
        this.readBooks();
    }
//    public void readBorrowed(){
//        books.clear();
//        books.addAll(repository.read(currentFilter, true));
//    }
    //
//    public void remove(List<Book> selectedItems) {
//        this.books.removeAll(selectedItems);
//    }
//
//    public void addbooks(List<Book> selectedItems) {
//        this.books.addAll(selectedItems);
//    }
//


//        public void filterList(String name, String email) {
//
//            List<Book> temp = new ArrayList<>();
//
//            books.forEach((Book p) -> {
//                try {
//                    if ((p.hasName(name)) || (p.hasEmail(email))) {
//                        temp.add(p);
//                    }
//                } catch (NumberFormatException e) {
//                }
//            });
//
//            this.current.clear();
//            this.current.addAll(temp);
//        }

}
