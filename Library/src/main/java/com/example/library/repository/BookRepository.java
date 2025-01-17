package com.example.library.repository;

import com.example.library.models.Book;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookRepository {

        public void create (Book book){

        }
        public Book read (int id){
            return new Book(UUID.randomUUID(),"s","s","s","2");
        }
        public List<Book> read (){
            return new ArrayList<Book>();
        }
        public void update (Book book){
        }
        public void delete (int id){

        }



//        public void dataBase() {
//                var url = "jdbc:sqlite:my.db";
//
//                // SQL statement for creating a new table
////        var sql = "DELETE FROM WAREHOUSES WHERE NAME = 'test1'";
//                var sql = "INSERT INTO WAREHOUSES(name,capacity) VALUES('test1', 2*10)";
//                //     "INSERT INTO WAREHOUSES(name,capacity) VALUES('test1', 2*10)"
//
//                try (var conn = DriverManager.getConnection(url);
//                     var stmt = conn.createStatement()) {
//                        // create a new table
//                        stmt.execute(sql);
//                } catch (SQLException e) {
//                        System.out.println(e.getMessage());
//                }
//
//        }

}


