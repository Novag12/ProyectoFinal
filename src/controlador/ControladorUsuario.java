/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/

package controlador;

import accesoDatos.DaoUsuario;
import logica.Usuario;

import java.util.List;

public class ControladorUsuario {
    DaoUsuario daoUsuario;

    public ControladorUsuario(){
        daoUsuario = new DaoUsuario();
    }

    public int insertarUsuario(String IdUsuario, String nombreUsuario, String direccion, String telefono, String email, String tipoUsuario){
        Usuario user = new Usuario();
        user.setIdUsuario(IdUsuario);
        user.setNombreUsuario(nombreUsuario);
        user.setDireccion(direccion);
        user.setTelefono(telefono);
        user.setEmail(email);
        user.setTipoUsuario(tipoUsuario);

        int result = daoUsuario.guardarUsuario(user);
        return result;
    }

    public Usuario consultarUsuario (String codigoUsuario){
        Usuario usuario = daoUsuario.consultarUsuario(codigoUsuario);
        return usuario;
    }

    public void modificarUsuario(Usuario user, String codigoUsuario){
        daoUsuario.modificarUsuario(user, codigoUsuario);
    }

    public void eliminarUsuario(String codigoUsuario){
        daoUsuario.borrarUsuario(codigoUsuario);
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> user = this.daoUsuario.listarUsuarios();
        return user;

    }

    public void cerrarConecionBD(){
        daoUsuario.cerrarConexionBD();
    }
}
