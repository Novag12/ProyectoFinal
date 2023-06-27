/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package controlador;

import accesoDatos.DaoEditorial;
import logica.AreaConocimiento;
import logica.Editorial;

import java.util.List;

public class ControladorEditorial {
    DaoEditorial daoEditorial;

    public ControladorEditorial(){daoEditorial = new DaoEditorial();}

    public int insertarEditorial(String codigo_editorial, String nombre_editorial, String paginaweb_editorial, String paisOrigen){
        Editorial editorial = new Editorial();
        editorial.setCodigoEditorial(codigo_editorial);
        editorial.setNombreEditorial(nombre_editorial);
        editorial.setPaginaWeb(paginaweb_editorial);
        editorial.setPaisOrigen(paisOrigen);

        int resultado = daoEditorial.guardarEditorial(editorial);
        return resultado;
    }

    public void modificarEditorial(String codigo){
        daoEditorial.modificarEditorial(codigo);
    }

    public void buscarEditorial(String codigo){
        daoEditorial.consultarEditorial(codigo);
    }

    public void eliminarEditorial(String codigo){
        daoEditorial.borrarEditorial(codigo);
    }

    public List<Editorial> listarEditorial(){
        List<Editorial> editorial = this.daoEditorial.listarEditorial();
        return editorial;

    }
    public void cerrarConexionBD(){
        daoEditorial.cerrarConexionBD();
    }
}
