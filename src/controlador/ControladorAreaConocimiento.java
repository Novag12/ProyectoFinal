/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package controlador;

import accesoDatos.DaoAreaConocimiento;
import logica.AreaConocimiento;

import java.util.List;

public class ControladorAreaConocimiento {
    DaoAreaConocimiento daoAreaConocimiento;

    public ControladorAreaConocimiento(){
        daoAreaConocimiento = new DaoAreaConocimiento();
    }

    public int insertarAreaConocimiento(String codigo_area, String nombre_area, String descripcion, String areaPadre){
        AreaConocimiento areaConocimiento = new AreaConocimiento();
        areaConocimiento.setCodigoArea(codigo_area);
        areaConocimiento.setNombreArea(nombre_area);
        areaConocimiento.setDescripcionArea(descripcion);
        areaConocimiento.setAreaPadre(areaPadre);

        int result =  daoAreaConocimiento.guardarAreaConocimiento(areaConocimiento);
        return result;
    }

    public AreaConocimiento consultarAreaConocimiento(String codigo_area){
        AreaConocimiento areaConocimiento = daoAreaConocimiento.consultarAreaConocimiento(codigo_area);
        return areaConocimiento;
    }

    public void modificarAreaConocimiento(String codigo_area){
        daoAreaConocimiento.modificarAreaConocimiento(codigo_area);
    }

    public void eliminarAreaConocimiento(String codigo_area){
        daoAreaConocimiento.borrarAreaConocimiento(codigo_area);
    }

    public List <AreaConocimiento> listarAreaConocimiento(){
        List<AreaConocimiento> area = this.daoAreaConocimiento.listarAreaConocimiento();
        return area;

    }

    public void cerrarConecionBD(){
        daoAreaConocimiento.cerrarConexionBD();
    }
}
