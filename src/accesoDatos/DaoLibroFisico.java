/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package accesoDatos;

import logica.LibroFisico;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoLibroFisico {

    FachadaBD fachada;

    public DaoLibroFisico(){
        fachada = new FachadaBD();
    }
    public void agregarLibroFisico(LibroFisico libroFisico) throws SQLException {

    }

    public void actualizarLibroFisico(LibroFisico libroFisico) throws SQLException {

    }

    public void borrarLibroFisico(String ISBN) {
        String sql_delete;
        sql_delete = "DELETE FROM LibroFisico WHERE ISBN ='" + ISBN + "'";

        try {
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            int filasafectadas = sentencia.executeUpdate(sql_delete);
            if (filasafectadas > 0) JOptionPane.showMessageDialog(null, "El registro se ha elimidano correctamente");
            else {
                System.out.println("El " + ISBN + "no existe en la base de datos");
            }
        }
        catch (SQLException e) { System.out.println(e);}
        catch (Exception e){ System.out.println(e);}
    }

        public LibroFisico consultarLibroFisico(String ISBN) throws SQLException {
        String query = "SELECT * FROM LibroFisico WHERE ISBN = ?";


        return null; // Si no se encuentra el libro físico, retorna null
    }

    //public List<LibroFisico> obtenerTodosLosLibrosFisicos() throws SQLException {

    //}
}
