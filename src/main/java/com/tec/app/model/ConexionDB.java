package com.tec.app.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://148.113.219.245:3306/teccahco_TEC";
    private static final String USER = "teccahco_administrador";
    private static final String PASSWORD = "MrQNggrZqXCRr05";
    private Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion establecida");
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return connection;
    }

    static void main() {
        ConexionDB conexion = new ConexionDB();
        Connection conn = conexion.getConnection();
        System.out.println(conn);
    }
}
