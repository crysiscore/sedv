/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author asamuel
 */
public class DialogUtil {
    
    public static void showInfoMessage(String message, String title) {
        showAlert(message, title, AlertType.INFORMATION);
    }

    public static void showWarningMessage(String message, String title) {
        showAlert(message, title, AlertType.WARNING);
    }

    public static void showErrorMessage(String message, String title) {
        showAlert(message, title, AlertType.ERROR);
    }

    private static void showAlert(String message, String title, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
