package com.example.library.repository;

import com.example.library.models.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

        public void create (Book book){

        }
        public Book read (int id){
            return new Book(2,"s","s","s",2);
        }
        public List<Book> read (){
            return new ArrayList<Book>();
        }
        public void update (Book book){
        }
        public void delete (int id){

        }

}


