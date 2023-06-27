/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package logica;

import java.util.Date;

public class SolicitudLibro {
    String numeroConsecutivo;
    Date fecha;
    String descripcion;
    String idUsuario;
    String ISBN;
    String nombreLibro;

    public SolicitudLibro(){}

    public String getNumeroConsecutivo(){
        return numeroConsecutivo;
    }
    public void setNumeroConsecutivo(String numeroConsecutivo){
        this.numeroConsecutivo = numeroConsecutivo;
    }
    public Date getFecha(){
        return fecha;
    }
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public String getIdUsuario(){
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario){
        this.idUsuario = idUsuario;
    }
    public String getISBN(){
        return ISBN;
    }
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }
    public String getNombreLibro(){
        return nombreLibro;
    }
    public void setNombreLibro(String nombreLibro){
        this.nombreLibro = nombreLibro;
    }
}
