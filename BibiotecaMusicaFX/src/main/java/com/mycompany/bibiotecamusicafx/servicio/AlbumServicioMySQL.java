package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlbumServicioMySQL implements AlbumServicio {

    private static Connection conexion;
    private static AlbumServicioMySQL instancia;

    private AlbumServicioMySQL() {
    }

    public static AlbumServicio getServicioMySQL() throws SQLException {
        if (conexion == null) {
            conexion = Conexion.getConexion();
        }
        if (instancia == null) {
            instancia = new AlbumServicioMySQL();
        }
        return instancia;
    }

    @Override
    public void guardar(Album album) {
        try {
            String sqlGuardar = "INSERT INTO album VALUES(?,?,?,?,?)";
            PreparedStatement stmt = conexion.prepareStatement(sqlGuardar);
            stmt.setString(1, album.getArtistaId());
            stmt.setString(2, album.getId());
            stmt.setString(3, album.getTitulo());
            stmt.setString(4, album.getGenero());
            stmt.setDate(5, album.getFechaLanzamiento());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(String id) {
        try {
            String sqlEliminar = "DELETE FROM album WHERE id LIKE ?";
            PreparedStatement stmt = conexion.prepareStatement(sqlEliminar);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editar(Album album) {
        try {
            String sqlEditar = "UPDATE album "
                    + "SET titulo = ?,"
                    + " artista_id = ?,"
                    + " genero = ?,"
                    + " fecha_lanzamiento = ? "
                    + "WHERE id LIKE ?";
            PreparedStatement stmt = conexion.prepareStatement(sqlEditar);
            stmt.setString(1, album.getTitulo());
            stmt.setString(2, album.getArtistaId());
            stmt.setString(3, album.getGenero());
            stmt.setDate(4, album.getFechaLanzamiento());
            stmt.setString(5, album.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Album> buscarConFiltro(Map filtro) {
        ArrayList<Album> albumes = new ArrayList<>();
        String nombre = (String) filtro.get("nombre");
        String genero = (String) filtro.get("genero");
        String sql = "SELECT * FROM album WHERE";
        if (nombre != null) {
            sql += " titulo LIKE ?";
            if (genero != null) {
                sql += " AND genero LIKE ?";
            }
        } else {
            sql += " genero LIKE ?";
        }
        
        Album album = null;
        PreparedStatement stmt;
        try {
            stmt = conexion.prepareStatement(sql);
            if (nombre != null) {
                stmt.setString(1, nombre);
                if (genero != null) {
                    stmt.setString(2, genero);
                }
            } else {
                stmt.setString(1, genero);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                albumes.add(new Album(
                        rs.getString("album.artista_id"),
                        rs.getString("album.titulo"),
                        rs.getString("album.genero"),
                        rs.getDate("album.fecha_lanzamiento"),
                        rs.getString("album.id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return albumes;
    }

    @Override
    public Album getAlbum(String id) {
        String sqlGetAlbum = "SELECT * FROM album WHERE id LIKE ?";
        Album album = null;
        PreparedStatement stmt;
        try {
            stmt = conexion.prepareStatement(sqlGetAlbum);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                album = new Album(
                        rs.getString("artista_id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getDate("fecha_lanzamiento"),
                        id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return album;
    }

    @Override
    public List<Album> obtenerTodos() {
        ArrayList<Album> albumes = new ArrayList<>();
        String sqlGetAll = "SELECT * FROM album";
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sqlGetAll);
            while (rs.next()) {
                albumes.add(new Album(rs.getString("artista_id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getDate("fecha_lanzamiento"),
                        rs.getString("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return albumes;
    }

    @Override
    public int contarAlbums() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarSesion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Album> getAlbumsByArtista(String artistaId) {
        ArrayList<Album> listaAlbumes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM album"
                    + " WHERE artista_id LIKE ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, artistaId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listaAlbumes.add(new Album(artistaId,
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getDate("fecha_lanzamiento"),
                        rs.getString("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAlbumes;
    }
}
