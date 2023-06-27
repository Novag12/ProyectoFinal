/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/

package accesoDatos;
import logica.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoAutor {
    FachadaBD fachada;

    public DaoAutor(){
        fachada = new FachadaBD();
    }

    public int guardarAutor(Autor autor) {
        String sql_guardar;
        sql_guardar = "INSERT INTO Autor(id_autor, primer_apellido, segundo_apellido, primer_nombre, segundo_nombre) VALUES ('" +
                autor.getCodigoAutor() + "' , '" + autor.getSurname() + "' , '" + autor.getSecondSurname() + "' , '" +
                autor.getFirstName() + "' , '" + autor.getSecondName() +"')";

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

    public Autor consultarAutor(String codigoAutor){
        Autor autor = new Autor();
        String sql_select;
        sql_select = "SELECT primer_apellido, segundo_apellido, primer_nombre, segundo_nombre FROM Autor WHERE id_autor = '" + codigoAutor + "'";

        try{
            System.out.println("consultando en la bd");
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()){
                autor.setCodigoAutor(tabla.getString(1));
                autor.setSurname(tabla.getString(2));
                autor.setSecondSurname(tabla.getString(3));
                autor.setFirstName(tabla.getString(4));
                autor.setSecondName(tabla.getString(5));
            }

            conn.close();
            System.out.println("Conexion Cerrada");

        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
        return autor;
    }

    public void modificarAutor(String codigoAutor){
        Autor autor = new Autor();
        String sql_update = "UPDATE Autor SET '" + autor.getSurname() + "' , '" + autor.getSecondSurname() + "' , '" +
                autor.getFirstName() + "' , '" + autor.getSecondName() + "' " + "WHERE id_autor = '" + codigoAutor + "'";
        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            if (sentencia.executeUpdate(sql_update) > 0) System.out.println("Se actualizó correctamente");
        }
        catch(SQLException e){ System.out.println(e);}
        catch(Exception e){ System.out.println(e);}
    }


    public List listarAutor(){
        List<Autor> autores = new ArrayList<>();
        PreparedStatement sentencia = null;
        String sql_list = "SELECT * FROM Autor";

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_list);
            ResultSet rs = sentencia.executeQuery();
            while(rs.next()){
                Autor autor = new Autor();
                autor.setCodigoAutor(rs.getString(1));
                autor.setSurname(rs.getString(2));
                autor.setSecondSurname(rs.getString(3));
                autor.setFirstName(rs.getString(4));
                autor.setSecondName(rs.getString(5));
                autores.add(autor);
            }
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }

        return autores;

    }

    public void borrarAutor(String codigoAutor){
        String sql_delete;
        sql_delete = "DELETE FROM Autor WHERE id_autor ='" + codigoAutor +  "'";
        PreparedStatement sentencia = null;
        int areasBorradas = 0;

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_delete);
            sentencia.setString(1,codigoAutor);
            areasBorradas = sentencia.executeUpdate();

            if(areasBorradas != 0){System.out.println("Borrada Correctamente");}
            else {System.out.println("El " + codigoAutor + "no existe en la base de datos");}
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
    }

    public void cerrarConexionBD(){
        fachada.closeConection(fachada.getConexion());
    }
}
