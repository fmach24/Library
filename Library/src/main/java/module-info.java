module com.example.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.library to javafx.fxml;
    exports com.example.library;
    exports com.example.library.models;
    opens com.example.library.models to javafx.fxml;
}