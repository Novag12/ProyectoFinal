/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package controlador;

import accesoDatos.DaoSolicitudLibro;
import logica.AreaConocimiento;
import logica.SolicitudLibro;

import java.util.Date;
import java.util.List;

public class ControladorSolicitudLibro {
    DaoSolicitudLibro daoSolicitudLibro;

    public ControladorSolicitudLibro(){
        daoSolicitudLibro = new DaoSolicitudLibro();
    }

    public int insertarSolicitudLibro(String numero_consecutivo, Date fecha_solicitud, String descripcionSolicitud, String id_usuario, String ISBN, String nombre_libro){
        SolicitudLibro solicitud = new SolicitudLibro();
        solicitud.setNumeroConsecutivo(numero_consecutivo);
        solicitud.setDescripcion(descripcionSolicitud);
        solicitud.setIdUsuario(id_usuario);
        solicitud.setISBN(ISBN);
        solicitud.setNombreLibro(nombre_libro);

        int result = daoSolicitudLibro.guardarSolicitudLibro(solicitud);
        return result;
    }

    public SolicitudLibro consultar(String numeroConsecutivo){
        SolicitudLibro solicitudLibro = daoSolicitudLibro.consultarSolicitudLibro(numeroConsecutivo);
        return solicitudLibro;

    }

    public void modificarSolicitud(String numeroConsecutivo){
        daoSolicitudLibro.modificarSolicitudLibro(numeroConsecutivo);
    }

    public void eliminarSolicitud(String numeroConsecutivo){
        daoSolicitudLibro.borrarSolicitudLibro(numeroConsecutivo);
    }

    public List<SolicitudLibro> listarSolicitudLibro(){
        List<SolicitudLibro> solicitud = this.daoSolicitudLibro.listarSolicitudLibro();
        return solicitud;
    }

    public void cerrarConexionBD(){
        daoSolicitudLibro.cerrarConexionBD();
    }
}
