/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package accesoDatos;
import logica.SolicitudLibro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoSolicitudLibro {
    FachadaBD fachada;

    public DaoSolicitudLibro(){
        fachada = new FachadaBD();
    }

    public int guardarSolicitudLibro(SolicitudLibro solicitud) {
        String sql_guardar;
        sql_guardar = "INSERT INTO SolicitudLibro(numero_consecutivo, fecha_solicitud, descripcionSolicitud, id_usuario, ISBN, nombre_libro) VALUES ('" +
                solicitud.getNumeroConsecutivo() + "' , '" + solicitud.getFecha() + "' , '" +
                solicitud.getDescripcion() + "' , '" + solicitud.getIdUsuario() + "' , '" + solicitud.getISBN() + "' , '" +
                solicitud.getNombreLibro() + "')";

        try {
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            int numFilas = sentencia.executeUpdate(sql_guardar);
            conn.close();
            return numFilas;

        }
        catch (SQLException e) { System.out.println(e);}
        catch (Exception e) {System.out.println(e);}

        return -1;

    }

    public SolicitudLibro consultarSolicitudLibro(String numeroConsecutivo){
        SolicitudLibro solicitudLibro = new SolicitudLibro();
        String sql_select;
        sql_select = "SELECT fecha_solicitud, descripcionSolicitud, id_usuario, ISBN, nombre_libro FROM AreaConocimiento WHERE numero_consecutivo = '" + numeroConsecutivo + "'";

        try{
            System.out.println("consultando en la bd");
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()){
                solicitudLibro.setNumeroConsecutivo(tabla.getString(1));
                solicitudLibro.setFecha(tabla.getDate(2));
                solicitudLibro.setDescripcion(tabla.getString(3));
                solicitudLibro.setIdUsuario(tabla.getString(4));
                solicitudLibro.setISBN(tabla.getString(5));
                solicitudLibro.setNombreLibro(tabla.getString(6));
            }

            conn.close();
            System.out.println("Conexion Cerrada");

        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }

        return solicitudLibro;
    }

    public void modificarSolicitudLibro(String numeroConsecutivo){
        SolicitudLibro solicitudLibro = new SolicitudLibro();
        String sql_update = "UPDATE SolicitudLibro SET '" + solicitudLibro.getFecha() + "' , '" +
                solicitudLibro.getDescripcion() + "' , '" + solicitudLibro.getIdUsuario() + "' , '" + solicitudLibro.getISBN() + "' , '" +
                solicitudLibro.getNombreLibro() + "' " + "WHERE numero_consecutivo = '" + numeroConsecutivo + "'";
        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            if (sentencia.executeUpdate(sql_update) > 0) System.out.println("Se actualizó correctamente");
        }
        catch(SQLException e){ System.out.println(e);}
        catch(Exception e){ System.out.println(e);}
    }

    public List listarSolicitudLibro(){
        List<SolicitudLibro> solicitudLibros = new ArrayList<>();
        String sql_listar = "SELECT * FROM SolicitudLibro";

        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet rs = sentencia.executeQuery(sql_listar);

            while(rs.next()){
                SolicitudLibro solicitud = new SolicitudLibro();
                solicitud.setNumeroConsecutivo(rs.getString(1));
                solicitud.setFecha(rs.getDate(2));
                solicitud.setDescripcion(rs.getString(3));
                solicitud.setIdUsuario(rs.getString(4));
                solicitud.setISBN(rs.getString(5));
                solicitud.setNombreLibro(rs.getString(6));
                solicitudLibros.add(solicitud);
            }

        }
        catch(SQLException e){ System.out.println(e);}
        catch(Exception e){ System.out.println(e);}

        return solicitudLibros;

    }

    public void borrarSolicitudLibro(String numeroConsecutivo){
        String sql_delete;
        sql_delete = "DELETE FROM SolicitudLibro WHERE numero_consecutivo ='" + numeroConsecutivo +  "'";
        PreparedStatement sentencia = null;
        int areasBorradas = 0;

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_delete);
            sentencia.setString(1,numeroConsecutivo);
            areasBorradas = sentencia.executeUpdate();

            if(areasBorradas != 0){System.out.println("Borrada Correctamente");}
            else {System.out.println("El " + numeroConsecutivo + "no existe en la base de datos");}
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
    }

    public void cerrarConexionBD(){
        fachada.closeConection(fachada.getConexion());
    }
}
