package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Album;
import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.model.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Album album) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Album> buscarConFiltro(Map filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Album getAlbum(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Album> obtenerTodos() {
        HashMap<String, Album> albums = new HashMap<>();
        ArrayList<Album> albumes = new ArrayList<>();
        String sqlGetAll = "SELECT artista.nombre, album.artista_id, album.id, "
                + "album.titulo, album.genero, album.fecha_lanzamiento"
                + " FROM album INNER JOIN artista ON artista.id = album.artista_id";
        Statement stmt;
        try {
            stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery(sqlGetAll);
            while (resultado.next()) {
                String artistaId = resultado.getString("album.artista_id");
                String id = resultado.getString("album.id");
                String titulo = resultado.getString("album.titulo");
                String genero = resultado.getString("album.genero");
                LocalDate fecha = resultado.getDate("album.fecha_lanzamiento").toLocalDate();
                albums.put(resultado.getString("artista.nombre"), new Album(artistaId, titulo, genero, fecha, id));
                //albumes.add(new Album(artistaId, titulo, genero, fecha, id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return albums;
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
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
