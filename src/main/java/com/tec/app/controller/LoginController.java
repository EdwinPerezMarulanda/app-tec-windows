package com.tec.app.controller;

import com.tec.app.Login;
import com.tec.app.model.ConexionLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    ConexionLogin conexion = new ConexionLogin();

    @FXML
    private Button btn_entrar;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_user;

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {

        String user = txt_user.getText();
        String password = txt_password.getText();

        //Validar que los campos no estén vacios
        if (user.isEmpty() || password.isEmpty()) {
            javafx.scene.control.Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacios");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingrese todos los campos.");
            alert.showAndWait();
            return;
        }

        //Validar usuario
        boolean loginExitoso = conexion.validarUsuario(user, password);

        if (loginExitoso) {
            //Limpiar campos
            txt_password.clear();
            // Aquí puedes cerrar la ventana de login y abrir la ventana principal
            btn_entrar.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/com/tec/app/docente-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Docente");
            stage.setMaximized(false);
            stage.setMinWidth(1200);
            stage.setMinHeight(800);
            stage.show();

        } else {
            //Limpiar solo el campo de password
            txt_password.clear();

        }

    }

}
