package com.example.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("log-in-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNIFIED);
        stage.getIcons().add(new Image("file:C:\\Users\\rafal\\IdeaProjects\\BibliotekaGitHub\\Library\\Library\\src\\main\\resources\\com\\example\\library\\logo2.png"));
        stage.show();
    }

    public static void main(String[] args) {

        String sqlBook =
                "CREATE TABLE IF NOT EXISTS Books (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, author TEXT NOT NULL, genre TEXT, publisher TEXT, isRented boolean, expiration datetime);";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:my.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlBook);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

//        String url = "jdbc:sqlite:my.db"; // Podmień na nazwę swojej bazy danych
//
//        // Komenda SQL dla tabeli books
//        String sql = "INSERT INTO books (title, author, genre, publisher, isRented, expiration) VALUES (?, ?, ?, ?, ?, ?)";
//
//        // Dane do wstawienia
//        Object[][] books = {
//                {"To Kill a Mockingbird", "Harper Lee", "Fiction", "J.B. Lippincott & Co.", 0, null},
//                {"1984", "George Orwell", "Dystopian", "Secker & Warburg", 0, null},
//                {"Pride and Prejudice", "Jane Austen", "Romance", "T. Egerton", 0, null},
//                {"The Great Gatsby", "F. Scott Fitzgerald", "Tragedy", "Charles Scribner's Sons", 0, null},
//                {"Moby-Dick", "Herman Melville", "Adventure", "Harper & Brothers", 0, null},
//                {"To Kill a Mockingbird", "Harper Lee", "Fiction", "Penguin Books", 0, null},
//                {"1984", "George Orwell", "Dystopian", "Penguin Books", 0, null},
//                {"Pride and Prejudice", "Jane Austen", "Romance", "Penguin Classics", 0, null},
//                {"The Catcher in the Rye", "J.D. Salinger", "Fiction", "Little, Brown and Company", 0, null},
//                {"Fahrenheit 451", "Ray Bradbury", "Dystopian", "Ballantine Books", 0, null},
//                {"Moby-Dick", "Herman Melville", "Adventure", "Modern Library", 0, null},
//                {"War and Peace", "Leo Tolstoy", "Historical Fiction", "Vintage Books", 0, null},
//                {"War and Peace", "Leo Tolstoy", "Historical Fiction", "The Russian Messenger", 0, null},
//                {"The Hobbit", "J.R.R. Tolkien", "Fantasy", "George Allen & Unwin", 0, null},
//                {"The Hobbit", "J.R.R. Tolkien", "Fantasy", "Houghton Mifflin", 0, null},
//                {"Animal Farm", "George Orwell", "Political Satire", "Harcourt, Brace and Company", 0, null},
//                {"Animal Farm", "George Orwell", "Political Satire", "Penguin Books", 0, null},
//                {"Jane Eyre", "Charlotte Brontë", "Romance", "Smith, Elder & Co.", 0, null},
//                {"Jane Eyre", "Charlotte Brontë", "Romance", "Penguin Classics", 0, null},
//                {"Crime and Punishment", "Fyodor Dostoevsky", "Psychological Fiction", "The Russian Messenger", 0, null},
//                {"Crime and Punishment", "Fyodor Dostoevsky", "Psychological Fiction", "Vintage Books", 0, null},
//                {"Dracula", "Bram Stoker", "Gothic Horror", "Archibald Constable and Company", 0, null},
//                {"Dracula", "Bram Stoker", "Gothic Horror", "Penguin Books", 0, null},
//                {"The Picture of Dorian Gray", "Oscar Wilde", "Philosophical Fiction", "Lippincott's Monthly Magazine", 0, null},
//                {"Frankenstein", "Mary Shelley", "Science Fiction", "Penguin Books", 0, null},
//                {"Frankenstein", "Mary Shelley", "Science Fiction", "Lackington, Hughes, Harding, Mavor & Jones", 0, null},
//                {"The Count of Monte Cristo", "Alexandre Dumas", "Adventure", "Penguin Classics", 0, null},
//                {"The Count of Monte Cristo", "Alexandre Dumas", "Adventure", "Émile Colin", 0, null},
//                {"A Tale of Two Cities", "Charles Dickens", "Historical Fiction", "Chapman & Hall", 0, null},
//                {"Great Expectations", "Charles Dickens", "Bildungsroman", "Penguin Books", 0, null},
//                {"Great Expectations", "Charles Dickens", "Bildungsroman", "Chapman & Hall", 0, null},
//                {"The Grapes of Wrath", "John Steinbeck", "Historical Fiction", "The Viking Press", 0, null},
//                {"Of Mice and Men", "John Steinbeck", "Fiction", "Penguin Books", 0, null},
//                {"Catch-22", "Joseph Heller", "Satire", "Simon & Schuster", 0, null},
//                {"Catch-22", "Joseph Heller", "Satire", "Vintage Books", 0, null},
//                {"Slaughterhouse-Five", "Kurt Vonnegut", "Science Fiction", "Delacorte Press", 0, null},
//                {"Slaughterhouse-Five", "Kurt Vonnegut", "Science Fiction", "Penguin Books", 0, null},
//                {"The Road", "Cormac McCarthy", "Post-Apocalyptic Fiction", "Alfred A. Knopf", 0, null},
//                {"Beloved", "Toni Morrison", "Historical Fiction", "Penguin Books", 0, null},
//                {"The Alchemist", "Paulo Coelho", "Quest", "HarperOne", 0, null},
//                {"Life of Pi", "Yann Martel", "Adventure", "Knopf Canada", 0, null},
//                {"The Kite Runner", "Khaled Hosseini", "Drama", "Riverhead Books", 0, null},
//                {"The Kite Runner", "Khaled Hosseini", "Drama", "Penguin Books", 0, null},
//                {"The Hunger Games", "Suzanne Collins", "Dystopian", "Scholastic Press", 0, null},
//                {"Catching Fire", "Suzanne Collins", "Dystopian", "Scholastic Press", 0, null},
//                {"Mockingjay", "Suzanne Collins", "Dystopian", "Penguin Books", 0, null},
//                {"The Fault in Our Stars", "John Green", "Romance", "Dutton Books", 0, null},
//                {"Paper Towns", "John Green", "Mystery", "Penguin Books", 0, null}
//        };
//
//        try (Connection conn = DriverManager.getConnection(url);
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            for (Object[] book : books) {
//                pstmt.setString(1, (String) book[0]); // title
//                pstmt.setString(2, (String) book[1]); // author
//                pstmt.setString(3, (String) book[2]); // genre
//                pstmt.setString(4, (String) book[3]); // publisher
//                pstmt.setInt(5, (Integer) book[4]);  // isRented
//                pstmt.setObject(6, book[5]);         // expiration (null)
//                pstmt.addBatch();
//            }
//
//            pstmt.executeBatch(); // Wykonanie wszystkich wierszy naraz
//            System.out.println("Books inserted successfully!");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }

        launch(args);
    }

}