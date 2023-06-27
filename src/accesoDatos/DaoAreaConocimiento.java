/*BASE DE DATOS DE LA BIBLIOTECA

   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package accesoDatos;
import logica.AreaConocimiento;

import java.awt.geom.Area;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoAreaConocimiento {
    FachadaBD fachada;

    public DaoAreaConocimiento(){
        fachada = new FachadaBD();
    }

    public int guardarAreaConocimiento(AreaConocimiento areac) {
        String sql_guardar;
        sql_guardar = "INSERT INTO AreaConocimiento(codigo_area, nombre_area, descripcion, codigo_areapadre) VALUES ('" +
                areac.getCodigoArea() + "' , '" + areac.getNombreArea() + "' , '" +
                areac.getDescripcionArea() + "' , '" + areac.getAreaPadre() + "')";

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

    public AreaConocimiento consultarAreaConocimiento(String codigoArea){
        AreaConocimiento area = new AreaConocimiento();
        String sql_select;
        sql_select = "SELECT codigo_area, nombre, descripcion, codigo_areapadre FROM AreaConocimiento WHERE codigo_area = '" + codigoArea + "'";

        try{
            System.out.println("consultando en la bd");
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()){
                area.setCodigoArea(tabla.getString(1));
                area.setNombreArea(tabla.getString(2));
                area.setDescripcionArea(tabla.getString(3));
                area.setAreaPadre(tabla.getString(4));
            }

            conn.close();
            System.out.println("Conexion Cerrada");

        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }

        return area;
    }

    public void modificarAreaConocimiento(String codigoArea){
        AreaConocimiento area = new AreaConocimiento();
        String sql_update = "UPDATE AreaConocimiento SET '" + area.getNombreArea() + "','" + area.getDescripcionArea() + "','" + area.getAreaPadre() + "' " + "WHERE codigo_area = '" + codigoArea + "'";
        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            if (sentencia.executeUpdate(sql_update) > 0) System.out.println("Se actualizó correctamente");
        }
        catch(SQLException e){ System.out.println(e);}
        catch(Exception e){ System.out.println(e);}
    }

    public List listarAreaConocimiento(){
        List<AreaConocimiento> area = new ArrayList<>();
        String sql_listar = "SELECT * FROM AreaConocimiento";

        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet rs = sentencia.executeQuery(sql_listar);

            while(rs.next()){
                AreaConocimiento a = new AreaConocimiento();
                a.setCodigoArea(rs.getString(1));
                a.setNombreArea(rs.getString(2));
                a.setDescripcionArea(rs.getString(3));
                a.setAreaPadre(rs.getString(4));
                area.add(a);
            }

        }
        catch(SQLException e){ System.out.println(e);}
        catch(Exception e){ System.out.println(e);}

        return area;

    }

    public void borrarAreaConocimiento(String codigoArea){
        String sql_delete;
        sql_delete = "DELETE FROM Autor WHERE id_autor ='" + codigoArea +  "'";
        PreparedStatement sentencia = null;
        int areasBorradas = 0;

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_delete);
            sentencia.setString(1,codigoArea);
            areasBorradas = sentencia.executeUpdate();

            if(areasBorradas != 0){System.out.println("Borrada Correctamente");}
            else {System.out.println("El " + codigoArea + "no existe en la base de datos");}
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
    }

    public void cerrarConexionBD(){
        fachada.closeConection(fachada.getConexion());
    }
}
