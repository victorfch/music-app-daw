package com.mycompany.bibiotecamusicafx.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {    
    private Conexion() {}

    public static Connection getConexion() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/music_project?serverTimezone=Portugal";
        String usuario = "root";
        String password = "";
        Connection conexion = DriverManager.getConnection(url, usuario, password);
        return conexion;
    }

}
