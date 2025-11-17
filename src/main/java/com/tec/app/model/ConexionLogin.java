package com.tec.app.model;

import com.tec.app.Login;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConexionLogin {

    // Método para validar usuario - recibe email y contraseña como parámetros
    public boolean validarUsuario(String email, String password) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        ConexionDB conexionBD = null;

        try {
            conexionBD = new ConexionDB();
            Connection conn = conexionBD.getConnection();

            // Buscar solo por email
            String consulta = "SELECT * FROM docente WHERE email = ?";
            ps = conn.prepareStatement(consulta);
            ps.setString(1, email.trim());

            rs = ps.executeQuery();

            if (rs.next()) {

                getData.getNombre = rs.getString("nombre");

                String id = rs.getString("id");
                String nombreUsuario = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cargo = rs.getString("cargo");
                String area = rs.getString("area");
                String emailProfesor = rs.getString("email");
                String hashedPassword = rs.getString("password");

                // Caso especial: primera vez, contraseña "1234"
                if (hashedPassword.equals("1234") && password.equals("1234")) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Cambio de Contraseña");
                    dialog.setHeaderText("Bienvenido: " + nombreUsuario);
                    dialog.setContentText("Por favor cambia tu contraseña para iniciar sesión:");

                    Optional<String> result = dialog.showAndWait();

                    if (result.isPresent() && !result.get().trim().isEmpty()) {
                        String nueva = result.get().trim();
                        String hashNueva = Hashed.hashPassword(nueva);

                        String actualizarContra = "UPDATE docente SET password = ? WHERE email = ?";
                        try (PreparedStatement psUpdate = conn.prepareStatement(actualizarContra)) {
                            psUpdate.setString(1, hashNueva);
                            psUpdate.setString(2, email);
                            psUpdate.executeUpdate();
                        }

                        mostrarAlerta("Éxito", "Contraseña actualizada con éxito. Vuelve a iniciar sesión.", Alert.AlertType.INFORMATION);
                        return false; // No inicia sesión aún
                    } else {
                        mostrarAlerta("Error", "La nueva contraseña no puede estar vacía.", Alert.AlertType.ERROR);
                        return false;
                    }
                }

                // Validar contraseña con BCrypt
                if (Hashed.checkPassword(password, hashedPassword)) {

                    return switch (cargo) {
                        case "PROFESOR", "GESTOR", "LÍDER" -> {
                            mostrarAlerta("Bienvenido", cargo + ": " + nombreUsuario + " " + apellido, Alert.AlertType.INFORMATION);
                            yield true;
                        }
                        default -> {
                            mostrarAlerta("Error", "Cargo no reconocido: " + cargo, Alert.AlertType.ERROR);
                            yield false;
                        }
                    };
                } else {
                    mostrarAlerta("Error de Autenticación", "Contraseña incorrecta", Alert.AlertType.ERROR);
                    return false;
                }

            } else {
                mostrarAlerta("Error de Autenticación", "Email no encontrado", Alert.AlertType.ERROR);
                return false;
            }

        } catch (SQLException e) {
            mostrarAlerta("Error de Conexión", "Error de base de datos: " + e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } catch (Exception e) {
            mostrarAlerta("Error", "Error inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexionBD != null) conexionBD.getConnection().close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    // Método helper para mostrar alertas de JavaFX
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
