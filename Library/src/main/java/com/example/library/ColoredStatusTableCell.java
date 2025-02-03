package com.example.library;

import com.example.library.models.Book;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;


    public class ColoredStatusTableCell extends TableCell<Book, String> {

        String backgroundtyle;
        public ColoredStatusTableCell(String backgroundstyle) {
            this.backgroundtyle = backgroundstyle;
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || getTableRow() == null) {
                setText(null);
                setGraphic(null);
            } else {
                Book row = (Book) getTableRow().getItem();
                setText(item.toString());
                setStyle(backgroundtyle + " -fx-text-fill: " + (row.statusLabel.get().equals("Dostępna") ? "green" : "red"));
                // If the statis is changing dynamic you have to add the following:
                row.statusLabel().addListener((observable, oldValue, newValue) ->
                        setStyle(backgroundtyle + " -fx-text-fill: " + (row.statusLabel.get().equals("Dostępna") ? "green" : "red")));
            }
        }
    }

