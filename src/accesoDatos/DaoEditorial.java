/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/

package accesoDatos;
import logica.Editorial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEditorial {
    FachadaBD fachada;

    public DaoEditorial(){
        fachada = new FachadaBD();
    }

    public int guardarEditorial(Editorial editorial) {
        String sql_guardar;
        sql_guardar = "INSERT INTO Editorial(codigo_editorial, nombre_editorial, paginaweb_editorial, pais_origen) VALUES ('" +
                editorial.getCodigoEditorial() + "' , '" + editorial.getNombreEditorial() + "' , '" +
                editorial.getPaginaWeb() + "' , '" + editorial.getPaisOrigen() +"')";

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

    public void consultarEditorial(String codigoEditorial){
        Editorial editorial = new Editorial();
        String sql_select;
        sql_select = "SELECT nombre_editorial, paginaweb_editorial, pais_origen FROM Editorial WHERE codigo_editorial = '" + codigoEditorial + "'";

        try{
            System.out.println("consultando en la bd");
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()){
                editorial.setCodigoEditorial(tabla.getString(1));
                editorial.setNombreEditorial(tabla.getString(2));
                editorial.setPaginaWeb(tabla.getString(3));
                editorial.setPaisOrigen(tabla.getString(4));
            }

            conn.close();
            System.out.println("Conexion Cerrada");

        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
    }

    public void modificarEditorial(String codigoEditorial){
        Editorial editorial = new Editorial();
        String sql_update = "UPDATE Editorial SET '" + editorial.getNombreEditorial() + "' , '" +
                editorial.getPaginaWeb() + "' , '" + editorial.getPaisOrigen() + "' " + "WHERE codigo_editorial = '" + codigoEditorial + "'";
        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            if (sentencia.executeUpdate(sql_update) > 0) System.out.println("Se actualizó correctamente");
        }
        catch(SQLException e){ System.out.println(e);}
        catch(Exception e){ System.out.println(e);}
    }


    public List listarEditorial(){
        List<Editorial> editoriales = new ArrayList<>();
        PreparedStatement sentencia = null;
        String sql_list = "SELECT * FROM Editorial";

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_list);
            ResultSet rs = sentencia.executeQuery();
            while(rs.next()){
                Editorial editorial = new Editorial();
                editorial.setCodigoEditorial(rs.getString(1));
                editorial.setNombreEditorial(rs.getString(2));
                editorial.setPaginaWeb(rs.getString(3));
                editorial.setPaisOrigen(rs.getString(4));
                editoriales.add(editorial);
            }
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }

        return editoriales;

    }

    public void borrarEditorial(String codigoEditorial){
        String sql_delete;
        sql_delete = "DELETE FROM Editorial WHERE codigo_editorial ='" + codigoEditorial +  "'";
        PreparedStatement sentencia = null;
        int areasBorradas = 0;

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_delete);
            sentencia.setString(1,codigoEditorial);
            areasBorradas = sentencia.executeUpdate();

            if(areasBorradas != 0){System.out.println("Borrada Correctamente");}
            else {System.out.println("El " + codigoEditorial + "no existe en la base de datos");}
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
    }

    public void cerrarConexionBD(){
        fachada.closeConection(fachada.getConexion());
    }
}