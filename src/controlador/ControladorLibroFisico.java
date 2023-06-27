/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package controlador;

import accesoDatos.DaoLibro;
import accesoDatos.DaoLibroFisico;
import logica.LibroFisico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ControladorLibroFisico {

    DaoLibroFisico daoLibroFisico;

    public  ControladorLibroFisico(){ daoLibroFisico = new DaoLibroFisico();}

    public void agregarLibroFisico(LibroFisico libroFisico) {
        try {
            daoLibroFisico.agregarLibroFisico(libroFisico);
            System.out.println("Libro físico agregado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el libro físico: " + e.getMessage());
        }
    }

    public void actualizarLibroFisico(LibroFisico libroFisico) {
        try {
            daoLibroFisico.actualizarLibroFisico(libroFisico);
            System.out.println("Libro físico actualizado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el libro físico: " + e.getMessage());
        }
    }

    public void borrarLibroFisico(String ISBN) throws SQLException {
        daoLibroFisico.borrarLibroFisico(ISBN);
    }

    public LibroFisico consultarLibroFisico(String ISBN) {
        try {
            return daoLibroFisico.consultarLibroFisico(ISBN);
        } catch (SQLException e) {
            System.out.println("Error al consultar el libro físico: " + e.getMessage());
            return null;
        }
    }
}

