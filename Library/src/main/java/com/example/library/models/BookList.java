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

    public BookList(BookRepository repository) {
        this.repository = repository;

//        current = FXCollections.<Book>observableArrayList();
//        List<Book> miau = BookRepository.read();
        books = FXCollections.observableArrayList(repository.read(null));
//        current.addAll(books);
//        books.addListener(new ListChangeListener<Book>() {
//            @Override
//            public void onChanged(javafx.collections.ListChangeListener.Change<? extends Book> c) {
//                current.clear();
//                current.addAll(books);
//            }
//
//
//        });
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
//       current.clear();
//       current.addAll(books);
       //todo zrobic zeby updatowala sis lista ksiazek
    }
//
//    public void addBook(String name, String email, String phone, String address, String ID, String type, int credits, double scholarship, String deductionCode) {
//        books.add(new Book(name, email, phone, address, ID, type, credits, scholarship, deductionCode));
//    }

//    public boolean hasBook(String name) {
//        for (Book e : books)
//            if (e.hasName(name) && !name.isEmpty())
//                return true;
//        return false;
//    }
//
//    public Book getBook(String name) {
//        for (Book p : books)
//            if (p.hasName(name))
//                return p;
//        return null;
//    }

    public Book readBook(int id) {
        return repository.read(id);
    }

    public void updateBorrow(Book book) {
        repository.updateBorrow(book);
    }

    public void removeBook(Book book) {
        repository.delete(book.idProperty().get());
        books.remove(book);

        //todo nie updatuje sie table view z jakiegos powodu
    }

    public void setFilter(String filter) {
        books.clear();
        books.addAll(repository.read(filter));
    }
    public void readBorrowed(){
        books.clear();
        books.addAll(repository.readBorrowed());
    }
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
