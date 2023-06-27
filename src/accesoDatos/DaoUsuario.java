/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/

package accesoDatos;

import logica.Autor;
import logica.Usuario;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {

    FachadaBD fachada;

    public DaoUsuario(){
        fachada = new FachadaBD();
    }

    public int guardarUsuario(Usuario user){
        String sql_guardar;
        sql_guardar = "INSERT INTO Usuario(id_usuario,nombre_usuario,direccion,telefono,email,tipo_usuario) VALUES ('" +
                user.getIdUsuario() + "' , '" + user.getNombreUsuario() + "' , '" + user.getDireccion() + "' , '" +
                user.getTelefono() + "' , '" + user.getEmail() + "' , '"+ user.getTipoUsuario() + "')";

        try {
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            int numFilas = sentencia.executeUpdate(sql_guardar);
            conn.close();
            return numFilas;

        }
        catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"El ID ya existe en la Base de datos");
        }
        catch (Exception e) {System.out.println(e);}

        return -1;
    }

    public Usuario consultarUsuario(String codigoUsuario){
        Usuario user = new Usuario();
        String sql_select;
        sql_select = "SELECT id_usuario,nombre_usuario,direccion,telefono,email,tipo_usuario FROM Usuario WHERE id_usuario = '" + codigoUsuario + "'";

        try{
            System.out.println("consultando en la bd");
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()){
                user.setIdUsuario(tabla.getString(1));
                user.setNombreUsuario(tabla.getString(2));
                user.setDireccion(tabla.getString(3));
                user.setTelefono(tabla.getString(4));
                user.setEmail(tabla.getString(5));
                user.setTipoUsuario(tabla.getString(6));
            }

            conn.close();
            System.out.println("Conexion Cerrada");

        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
        return user;
    }

    public void modificarUsuario(Usuario user, String codigoUsuario){
        //Usuario user = new Usuario();
        String sql_update = "UPDATE Usuario SET nombre_usuario = '" + user.getNombreUsuario() + "', direccion = '" + user.getDireccion() + "', telefono = '" + user.getTelefono() + "', email = '" + user.getEmail() + "', tipo_usuario = '" + user.getTipoUsuario() + "' WHERE id_usuario = '" + codigoUsuario + "'";
        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            if (sentencia.executeUpdate(sql_update) > 0) JOptionPane.showMessageDialog(null,"Se actualizó correctamente");
        }
        catch(SQLException e){ System.out.println(e);}
        catch(Exception e){ System.out.println(e);}
    }

    public List listarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement sentencia = null;
        String sql_list = "SELECT * FROM Usuario";

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_list);
            ResultSet rs = sentencia.executeQuery();
            while(rs.next()){
                Autor autor = new Autor();
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getString(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setDireccion(rs.getString(3));
                usuario.setTelefono(rs.getString(4));
                usuario.setEmail(rs.getString(5));
                usuario.setTipoUsuario(rs.getString(6));
                usuarios.add(usuario);
            }
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }

        return usuarios;

    }
    public void borrarUsuario(String codigoUsuario){
        String sql_delete;
        sql_delete = "DELETE FROM Usuario WHERE id_usuario ='" + codigoUsuario +  "'";

        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            int filasafectadas = sentencia.executeUpdate(sql_delete);
            if(filasafectadas > 0) JOptionPane.showMessageDialog(null, "El registro se ha eliminado correctamente");
            else {System.out.println("El " + codigoUsuario + "no existe en la base de datos");}
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
    }

    public void cerrarConexionBD(){
        fachada.closeConection(fachada.getConexion());
    }
}
