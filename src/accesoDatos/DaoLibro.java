/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package accesoDatos;

import GUI.Libros;
import logica.Libro;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoLibro {
    FachadaBD fachada;
    Connection connection; // Agrega una variable de conexión

    public DaoLibro() {
        fachada = new FachadaBD();
        connection = fachada.openConnection(); // Obtener la conexión de la fachad
    }

    public int guardarLibro(Libro lib) {
        String sql_guardar;
        sql_guardar = " INSERT INTO Libro(ISBN, titulo_libro, anho_publicacion, idioma_libro, numero_paginas, codigo_editorial) VALUES ( '" +
                lib.getISBN() + "' , '" + lib.getTituloLibro() + "' , '" + lib.getAnhoPublicacion() + "' , '" + lib.getIdiomaLibro() + "' , '"
                + lib.getNumeroPaginas() + "' , '" + lib.getCodigoEditorial() + "')";

        try {
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            int numFilas = sentencia.executeUpdate(sql_guardar);
            conn.close();
            return numFilas;

        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "El ID ya existe en la Base de datos");
        } catch (Exception e) {
            System.out.println(e);
        }

        return -1;
    }

    public void agregarLibro(Libro libro) throws SQLException {
        String query = "INSERT INTO Libro (ISBN, titulo_libro, anho_publicacion, idioma_libro, numero_paginas, codigo_editorial) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, libro.getISBN());
            statement.setString(2, libro.getTituloLibro());
            statement.setString(3, libro.getAnhoPublicacion());
            statement.setString(4, libro.getIdiomaLibro());
            statement.setString(5, libro.getNumeroPaginas());
            statement.setString(6, libro.getCodigoEditorial());

            statement.executeUpdate();
        }
    }

    public List<Libro> obtenerTodosLosLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        PreparedStatement sentencia = null;
        String sql_list = "SELECT * FROM Libro";


        try {
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_list);
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setISBN(rs.getString(1));
                libro.setTituloLibro(rs.getString(2));
                libro.setAnhoPublicacion(rs.getString(3));
                libro.setIdiomaLibro(rs.getString(4));
                libro.setNumeroPaginas(rs.getString(5));
                libro.setCodigoEditorial(rs.getString(6));
                libros.add(libro);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

        return libros;
    }


    public void modificarLibro(Libro lib, String codISBN){
        String sql_update = "UPDATE Libro SET ISBN = '" + lib.getISBN() + "' , titulo_libro ='" + lib.getTituloLibro() + "' ,anho_publicacion '" + lib.getAnhoPublicacion() + "' ,idioma_libro '" + lib.getIdiomaLibro() + "' ,numero_paginas '"
                + lib.getNumeroPaginas() + "' , '" + lib.getCodigoEditorial() + "')";

        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            if (sentencia.executeUpdate(sql_update) >0);
        }
        catch (SQLException e) { System.out.println(e);}
        catch(Exception e){ System.out.println(e);}
        }


    public void borrarLibro(String ISBN) throws SQLException {
        String query = "DELETE FROM Libro WHERE ISBN = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ISBN);

            statement.executeUpdate();
        }
    }

    public List listarLibros(){
        List<Libro> libros = new ArrayList<>();
        PreparedStatement sentencia = null;
        String sql_list = "SELECT * FROM Libro";

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_list);
            ResultSet rs = sentencia.executeQuery();
            while(rs.next()){
                Libro libro = new Libro();
                libro.setISBN(rs.getString(1));
                libro.setTituloLibro(rs.getString(2));
                libro.setAnhoPublicacion(rs.getString(3));
                libro.setIdiomaLibro(rs.getString(4));
                libro.setNumeroPaginas(rs.getString(5));
                libro.setCodigoEditorial(rs.getString(6));
                libros.add(libro);
            }
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }

        return libros;

    }
    public Libro consultarLibro(String ISBN) throws SQLException {
        Libro lib = new Libro();
        String sql_select;
        sql_select = "SELECT ISBN, titulo_libro, anho_publicacion, idioma_libro, numero_paginas, codigo_editorial FROM Libro WHERE ISBN = " + ISBN;

        try {
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()) {
                lib.setISBN(tabla.getString(1));
                lib.setTituloLibro(tabla.getString(2));
                lib.setAnhoPublicacion(tabla.getString(3));
                lib.setIdiomaLibro(tabla.getString(4));
                lib.setNumeroPaginas(tabla.getString(5));
                lib.setCodigoEditorial(tabla.getString(6));
            }
            conn.close();
            System.out.println("Conexion Cerrada");
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return lib;
    }
}
