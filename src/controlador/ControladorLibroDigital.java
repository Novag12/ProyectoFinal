/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package controlador;

import accesoDatos.DaoLibroDigital;
import logica.LibroDigital;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControladorLibroDigital {
    private DaoLibroDigital daoLibroDigital;

    public ControladorLibroDigital(Connection connection) {
        daoLibroDigital = new DaoLibroDigital(connection);
    }

    public void agregarLibroDigital(LibroDigital libroDigital) {
        try {
            daoLibroDigital.agregarLibroDigital(libroDigital);
            System.out.println("Libro digital agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el libro digital: " + e.getMessage());
        }
    }

    public void actualizarLibroDigital(LibroDigital libroDigital) {
        try {
            daoLibroDigital.actualizarLibroDigital(libroDigital);
            System.out.println("Libro digital actualizado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el libro digital: " + e.getMessage());
        }
    }

    public void borrarLibroDigital(String ISBN) {
        try {
            daoLibroDigital.borrarLibroDigital(ISBN);
            System.out.println("Libro digital borrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al borrar el libro digital: " + e.getMessage());
        }
    }

    public LibroDigital consultarLibroDigital(String ISBN) {
        try {
            return daoLibroDigital.consultarLibroDigital(ISBN);
        } catch (SQLException e) {
            System.out.println("Error al consultar el libro digital: " + e.getMessage());
            return null;
        }
    }

    public List<LibroDigital> obtenerTodosLosLibrosDigitales() {
        try {
            return daoLibroDigital.obtenerTodosLosLibrosDigitales();
        } catch (SQLException e) {
            System.out.println("Error al obtener los libros digitales: " + e.getMessage());
            return null;
        }
    }
}
