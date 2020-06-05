package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArtistaServicioMySQL implements ArtistaServicio {
    
    private static Connection conexion;
    private static ArtistaServicioMySQL instancia;
    
    private ArtistaServicioMySQL() {}
    
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
    public void eliminar(String id) {
        try {
            String sqlEliminar = "DELETE FROM artista WHERE id LIKE ?";
            PreparedStatement stmt = conexion.prepareStatement(sqlEliminar);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void editar(Artista artista) {
        try {
            String sqlEditar = "UPDATE artista "
                    + "SET nombre = ?,"
                    + " nacionalidad = ?,"
                    + " fecha_nacimiento = ? "
                    + "WHERE id LIKE ?";
            PreparedStatement stmt = conexion.prepareStatement(sqlEditar);
            stmt.setString(1, artista.getNombre());
            stmt.setString(2, artista.getNacionalidad());
            stmt.setDate(3, artista.getFechaNacimientoDate());
            stmt.setString(4, artista.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ArrayList<Artista> obtenerTodos() {
        ArrayList<Artista> artistas = new ArrayList<>();
        String sqlGetAll = "SELECT * FROM artista";
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery(sqlGetAll);
            while (resultado.next()) {
                String id = resultado.getString("id");
                String nombre = resultado.getString("nombre");
                String nacionalidad = resultado.getString("nacionalidad");
                LocalDate fecha = resultado.getDate("fecha_nacimiento").toLocalDate();
                artistas.add(new Artista(nombre, nacionalidad, fecha, id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artistas;
    }
    
    @Override
    public int contarArtistas() {
        int contador = 0;
        try {
            String sql = "SELECT COUNT(*) FROM artista";
            Statement stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);
            resultado.next();
            contador = resultado.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contador;
    }
    
    @Override
    public Artista getArtista(String id) {
        String sqlGetArtista = "SELECT * FROM artista WHERE id LIKE ?";
        Artista artista = null;
        PreparedStatement stmt;
        try {
            stmt = conexion.prepareStatement(sqlGetArtista);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                artista = new Artista(
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artista;
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
        if (conexion == null) {
            conexion = Conexion.getConexion();
        }
        if (instancia == null) {
            instancia = new ArtistaServicioMySQL();
        }
        return instancia;
    }
    
    @Override
    public ArrayList<Artista> buscarConFiltro(Map filtro) {
        ArrayList<Artista> artistas = new ArrayList<>();
        
        String nombre = (String) filtro.get("nombre");
        String nacionalidad = (String) filtro.get("nacionalidad");
        String sql = "SELECT * FROM artista WHERE";
        if (nombre != null) {
            sql += " nombre LIKE ?";
            if (nacionalidad != null) {
                sql += " AND nacionalidad LIKE ?";
            }
        } else {
            sql += " nacionalidad LIKE ?";
        }
        
        Artista artista = null;
        PreparedStatement stmt;
        try {
            stmt = conexion.prepareStatement(sql);
            if (nombre != null) {
                stmt.setString(1, nombre);
                if (nacionalidad != null) {
                    stmt.setString(2, nacionalidad);
                }
            } else {
                stmt.setString(1, nacionalidad);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                artista = new Artista(
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("id")
                );
                artistas.add(artista);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artistas;
    }
}
