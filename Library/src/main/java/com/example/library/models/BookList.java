package com.example.library.models;

import com.example.library.Listener;
import com.example.library.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.collections.ListChangeListener;

public class BookList {

    private ObservableList<Book> books;
    private ObservableList<Book> current;
    private BookRepository repository;

    public BookList(BookRepository repository) {
        this.repository = repository;

        current = FXCollections.<Book>observableArrayList();
//        List<Book> miau = BookRepository.read();
        books = FXCollections.observableArrayList(repository.read());
        current.addAll(books);
        books.addListener(new ListChangeListener<Book>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends Book> c) {
                current.clear();
                current.addAll(books);
            }
        });
    }

    public ObservableList<Book> getCurrentList() {
        return this.current;
    }

    public void addBook(Book book) {
        book.id = repository.create(book);
        books.add(book);
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

    public void remove(Book book) {
        repository.delete(book.id);
        this.books.remove(book);
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
