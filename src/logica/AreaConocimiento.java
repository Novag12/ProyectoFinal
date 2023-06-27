/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package logica;

public class AreaConocimiento {
    String codigo_area;
    String nombre_area;
    String descripcion;
    AreaConocimiento areaPadre;
    String areapadre = areaPadre.toString();

    public AreaConocimiento(){}
    public String getCodigoArea(){
        return codigo_area;
    }
    public void setCodigoArea(String codigo_area){ this.codigo_area = codigo_area; }
    public String getNombreArea(){
        return nombre_area;
    }
    public void setNombreArea(String nombre_area){
        this.nombre_area = nombre_area;
    }
    public String getDescripcionArea(){
        return descripcion;
    }
    public void setDescripcionArea(String descripcion){
        this.descripcion = descripcion;
    }
    public AreaConocimiento getAreaPadre(){ return areaPadre;}
    public void setAreaPadre(String areapadre){
        this.areapadre = areapadre;
    }

}
