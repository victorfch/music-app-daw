package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Cancion;
import com.mycompany.bibiotecamusicafx.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CancionServicioMySQL {
    private static Connection conexion;
    private static CancionServicioMySQL instancia;

    private CancionServicioMySQL() {
    }

    public static CancionServicioMySQL getServicioMySQL() throws SQLException {
        if (conexion == null) {
            conexion = Conexion.getConexion();
        }
        if (instancia == null) {
            instancia = new CancionServicioMySQL();
        }
        return instancia;
    }

    public void guardar(Cancion cancion) {
        try {
            String sqlGuardar = "INSERT INTO cancion VALUES(?,?,?,?,?)";
            PreparedStatement stmt = conexion.prepareStatement(sqlGuardar);
            stmt.setString(1, cancion.getTitulo());
            stmt.setString(2, cancion.getIdArtista());
            stmt.setString(3, cancion.getIdAlbum());
            stmt.setString(4, cancion.getLetra());
            stmt.setString(5, cancion.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CancionServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(String id) {
        try {
            String sqlEliminar = "DELETE FROM cancion WHERE id LIKE ?";
            PreparedStatement stmt = conexion.prepareStatement(sqlEliminar);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CancionServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cancion> obtenerTodos() {
        ArrayList<Cancion> canciones = new ArrayList<>();
        String sqlGetAll = "SELECT * FROM cancion";
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sqlGetAll);
            while (rs.next()) {
                canciones.add(new Cancion(rs.getString("titulo"),
                        rs.getString("artista_id"),
                        rs.getString("album_id"),
                        rs.getString("letra"),
                        rs.getString("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancionServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return canciones;
    }

    public void cerrarSesion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(CancionServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
