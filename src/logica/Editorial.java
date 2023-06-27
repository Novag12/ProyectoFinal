/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package logica;

public class Editorial {
    String codigo_editorial;
    String nombre_editorial;
    String paginaweb_editorial;
    String paisOrigen;

    public Editorial(){}

    public String getCodigoEditorial(){
        return codigo_editorial;
    }
    public void setCodigoEditorial(String codigo_editorial){
        this.codigo_editorial = codigo_editorial;
    }
    public String getNombreEditorial(){
        return nombre_editorial;
    }
    public void setNombreEditorial(String nombre_editorial){
        this.nombre_editorial = nombre_editorial;
    }
    public String getPaginaWeb(){
        return paginaweb_editorial;
    }
    public void setPaginaWeb(String paginaweb_editorial){
        this.paginaweb_editorial = paginaweb_editorial;
    }
    public String getPaisOrigen(){
        return paisOrigen;
    }
    public void setPaisOrigen(String paisOrigen){
        this.paisOrigen = paisOrigen;
    }
}
