package com.example.library.repository;

import com.example.library.models.Book;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookRepository {


    public int create(Book book) {
        String sql = "INSERT INTO books(title, author, genre, publisher, isRented) VALUES( ?, ?, ?, ?, ?) RETURNING id";
        try (var conn = DriverManager.getConnection("jdbc:sqlite:my.db");
             var pstmt = conn.prepareStatement(sql)) {
//
            pstmt.setString(1, book.title.get());
            pstmt.setString(2, book.author.get());
            pstmt.setString(3, book.genre.get());
            pstmt.setString(4, book.publisher.get());
            pstmt.setBoolean(5, book.isRented.get());
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }


    public Book read(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (var conn = DriverManager.getConnection("jdbc:sqlite:my.db");
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            var rs = pstmt.executeQuery();
            if (rs.next()) {

                if(rs.getString("expiration")!=null) {
                    String fakeDateTime = rs.getString("expiration");
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime realDateTime = LocalDateTime.parse(fakeDateTime);
                    Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("publisher"), rs.getBoolean("isRented"), realDateTime);
                    return book;
                }
                else {
                    Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("publisher"), rs.getBoolean("isRented"));
                    return book;
                }




                //TODO nie bedzie działac gdy jest null trzeba zrobic if czy cos tego typu
//                String fakeDateTime = rs.getString("expiration");
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                LocalDateTime realDateTime = LocalDateTime.parse(fakeDateTime, formatter);
//                return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("publisher"), rs.getBoolean("isRented"), realDateTime);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public List<Book> read(String filter) {
        List<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM Books";
        if(filter!=null)
        {
            sql += " WHERE TITLE LIKE '%" + filter+"%' OR AUTHOR LIKE '%" + filter+"%' OR GENRE LIKE '%" + filter+"%'";

        }
        try (var conn = DriverManager.getConnection("jdbc:sqlite:my.db");
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if(rs.getString("expiration")!=null) {
                    String fakeDateTime = rs.getString("expiration");
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss");
                    LocalDateTime realDateTime = LocalDateTime.parse(fakeDateTime);
                    Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("publisher"), rs.getBoolean("isRented"), realDateTime);
                    books.add(book);
                }
                else {
                    Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("publisher"), rs.getBoolean("isRented"));
                    books.add(book);
                }
//                System.out.println(realDateTime);
//                LocalDateTime miau = LocalDateTime.parse("2004-02-23 2:21:34");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    public List<Book> readBorrowed() {
        List<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM Books WHERE isRented = 1";

        try (var conn = DriverManager.getConnection("jdbc:sqlite:my.db");
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if(rs.getString("expiration")!=null) {
                    String fakeDateTime = rs.getString("expiration");
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss");
                    LocalDateTime realDateTime = LocalDateTime.parse(fakeDateTime);
                    Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("publisher"), rs.getBoolean("isRented"), realDateTime);
                    books.add(book);
                }
                else {
                    Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("publisher"), rs.getBoolean("isRented"));
                    books.add(book);
                }
//                System.out.println(realDateTime);
//                LocalDateTime miau = LocalDateTime.parse("2004-02-23 2:21:34");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    public void update(Book book) {
        String sql = "UPDATE Books SET title = ?, author = ?, genre = ?, publisher = ? WHERE id = ?";
        try (var conn = DriverManager.getConnection("jdbc:sqlite:my.db");
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.title.get());
            pstmt.setString(2, book.author.get());
            pstmt.setString(3, book.genre.get());
            pstmt.setString(4, book.publisher.get());
            pstmt.setInt(5, book.id.get());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //TODO DODAC UPDATE DO STANU WYPOZYCZENIA (WYPOZYCZ - ODDAJ) sprawdzic czy dobrze zaimplementowane
    public void updateBorrow(Book book) {
        String sql = "UPDATE Books SET isRented = ?, expiration = ? WHERE id = ?";
        try (var conn = DriverManager.getConnection("jdbc:sqlite:my.db");
             var pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, book.isRented.get());
            pstmt.setString(2, book.expiration.get() != null ? book.expiration.get().toString() : null);
            pstmt.setInt(3, book.id.get());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    public void returning(Book book) {
//        String sql = "UPDATE Books SET isRented = ? expiration = ? WHERE id = ?";
//        try (var conn = DriverManager.getConnection("jdbc:sqlite:my.db");
//             var pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setBoolean(1, book.isRented.get());
//            pstmt.setString(2, book.expiration.get().toString());
//            pstmt.setInt(3, book.id.get());
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }



    public void delete(int id) {
        String sql = "DELETE FROM Books WHERE id = ?";
        try (var conn = DriverManager.getConnection("jdbc:sqlite:my.db");
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




//        public void create (Book book){
//
//        }
//        public Book read (int id){
//            return new Book(UUID.randomUUID(),"s","s","s","2");
//        }
//        public List<Book> read (){
//
//            return new ArrayList<Book>();
//        }
//

//        public void update (Book book){
//            var url = "jdbc:sqlite:my.db";
//
//
//            StringProperty amount = book.amount;
////            id = id.toString();
//
//
//            try (Connection conn = DriverManager.getConnection(url)){
//
//            }
//                String sql = "UPDATE INTO Books (id, title, author, genre, amount) VALUES (?,?,?,?,?)";
//                PreparedStatement stmt = conn.prepareStatement(sql);
//                stmt.setInt(1, title);
//                stmt.setString(2, author);
//                stmt.setString(3, genre);
//                stmt.setString(4, amount);
//                stmt.setInt(5, amount);
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//
//
//
//
//            try (Connection conn = DriverManager.getConnection(StartApplication.db_url)){
//
//                //TODO: make time of rental based on points.
//                String query = "INSERT INTO book_rental (book_id, user_id, deadline) VALUES (?,?,?)";
//                PreparedStatement stmt = conn.prepareStatement(sql);
//                stmt.setInt(1, b.getId());
//                stmt.setInt(2, user.getId());
//                stmt.setDate(3, Date.valueOf(deadline));
//                LocalDate deadline = LocalDate.now().plusDays(LibraryRules.getBorrowPeriodLength(user.getPoints()));
//                stmt.setDate(3, Date.valueOf(deadline));
//
//
//            }


//        public void delete (int id){
//                var url = "jdbc:sqlite:my.db";
////                id.toString();
//                var sql = "DELETE FROM WAREHOUSES WHERE ID = $id";
//
//                try (var conn = DriverManager.getConnection(url);
//                     var stmt = conn.createStatement()) {
//                    stmt.execute(sql);
//                } catch (SQLException e) {
//                    System.out.println(e.getMessage());
//                }
//        }



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


