package com.mycompany.bibiotecamusicafx.servicio;

import com.mycompany.bibiotecamusicafx.model.Artista;
import com.mycompany.bibiotecamusicafx.model.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
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
            stmt.setDate(4, artista.getFechaNacimiento());
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
            stmt.setDate(3, artista.getFechaNacimiento());
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
                Date fecha = resultado.getDate("fecha_nacimiento");
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
                        rs.getDate("fecha_nacimiento"),
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
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("id")
                );
                artistas.add(artista);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artistas;
    }

    @Override
    public void crearTablas() {
        String sqlTablaCancion = "CREATE TABLE IF NOT EXISTS cancion ("
                + "titulo varchar(20) COLLATE utf8_spanish_ci NOT NULL,"
                + "artista_id varchar(36) COLLATE utf8_spanish_ci NOT NULL,"
                + "album_id varchar(36) COLLATE utf8_spanish_ci NOT NULL,"
                + "letra text COLLATE utf8_spanish_ci NOT NULL,"
                + "id varchar(36) COLLATE utf8_spanish_ci NOT NULL,"
                + "PRIMARY KEY (id),"
                + "KEY cancion_ibfk_1 (artista_id),"
                + "KEY album_id (album_id),"
                + "CONSTRAINT cancion_ibfk_1 FOREIGN KEY (artista_id) "
                + "REFERENCES album (artista_id) ON DELETE CASCADE ON UPDATE CASCADE,"
                + "CONSTRAINT cancion_ibfk_2 FOREIGN KEY (album_id) "
                + "REFERENCES album (id) ON DELETE CASCADE ON UPDATE CASCADE"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci";

        String sqlTablaAlbum = "CREATE TABLE IF NOT EXISTS album ("
                + " artista_id varchar(36) COLLATE utf8_spanish_ci NOT NULL,"
                + " id varchar(36) COLLATE utf8_spanish_ci NOT NULL,"
                + " titulo varchar(20) COLLATE utf8_spanish_ci NOT NULL,"
                + " genero varchar(15) COLLATE utf8_spanish_ci NOT NULL,"
                + " fecha_lanzamiento date NOT NULL,"
                + " UNIQUE KEY id (id),"
                + " KEY artista_id (artista_id) USING BTREE,"
                + " CONSTRAINT album_ibfk_1 FOREIGN KEY (artista_id)"
                + " REFERENCES artista (id) ON DELETE CASCADE ON UPDATE CASCADE"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci";

        String sqlTablaArtista = "CREATE TABLE IF NOT EXISTS artista ("
                + " id varchar(36) COLLATE utf8_spanish_ci NOT NULL,"
                + " nombre varchar(20) COLLATE utf8_spanish_ci NOT NULL,"
                + " nacionalidad varchar(20) COLLATE utf8_spanish_ci NOT NULL,"
                + " fecha_nacimiento date NOT NULL,"
                + " PRIMARY KEY (id)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci";

        Statement sentencia;
        try {
            sentencia = conexion.createStatement();
            sentencia.execute(sqlTablaArtista);
            sentencia.execute(sqlTablaAlbum);
            sentencia.execute(sqlTablaCancion);
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaServicioMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
