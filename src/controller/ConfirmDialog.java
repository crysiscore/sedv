/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
/**
 *
 * @author asamuel
 */
public class ConfirmDialog {
    /**
     * Displays a confirmation dialog with the specified title, header, and content.
     *
     * @param title   the title of the dialog
     * @param header  the header text of the dialog
     * @param content the content text of the dialog
     * @return true if the user clicked OK, false otherwise
     */
    public static boolean show(String title, String header, String content) {
        // Create an Alert of type CONFIRMATION
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Show the dialog and wait for a response
        Optional<ButtonType> result = alert.showAndWait();

        // Return true if the user chose OK, false otherwise
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
