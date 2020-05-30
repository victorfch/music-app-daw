package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArtistaServicioMySQL implements ArtistaServicio {

    private static Connection conexion;
    private static ArtistaServicioMySQL instancia;

    private ArtistaServicioMySQL() {
    }

    @Override
    public void guardar(Artista artista) {
        try {
            String sqlGuardar = "INSERT INTO artista VALUES(?,?,?,?)";
            PreparedStatement stmt = conexion.prepareStatement(sqlGuardar);
            stmt.setString(1, artista.getId());
            stmt.setString(2, artista.getNombre());
            stmt.setString(3, artista.getNacionalidad());
            stmt.setDate(4, artista.getFechaNacimientoDate());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editar(Artista artista) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Artista> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int contarArtistas() {
        int contador = 0;
//        try {
//            String sql = "SELECT COUNT(*) FROM artista";
//            Statement stmt = conexion.createStatement();
//            ResultSet resultado = stmt.executeQuery(sql);
//            resultado.next();
//            contador = resultado.getInt(1);
//        } catch (SQLException ex) {
//            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return contador;
    }

    @Override
    public Artista getArtista(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cerrarSesion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArtistaServicio getServicioMySQL() throws SQLException {
        if (instancia == null) {
            conexion = Conexion.getConexion();
            instancia = new ArtistaServicioMySQL();
        }
        return instancia;
    }

}
