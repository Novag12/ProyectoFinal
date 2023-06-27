/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package controlador;

import accesoDatos.DaoAutor;
import logica.AreaConocimiento;
import logica.Autor;

import java.util.List;

public class ControladorAutor {
    DaoAutor daoAutor;

    public ControladorAutor(){daoAutor = new DaoAutor();}

    public int insertarAutor(String id_autor, String primer_apellido, String segundo_apellido, String primer_nombre, String segundo_nombre){
        Autor autor = new Autor();
        autor.setCodigoAutor(id_autor);
        autor.setSurname(primer_apellido);
        autor.setSecondSurname(segundo_apellido);
        autor.setFirstName(primer_nombre);
        autor.setSecondName(segundo_nombre);

        int result = daoAutor.guardarAutor(autor);
        return result;
    }

    public Autor consultarAutor(String codigo){
        Autor autor = daoAutor.consultarAutor(codigo);
        return autor;
    }

    public List<Autor> listarAutor(){
        List<Autor> autor = this.daoAutor.listarAutor();
        return autor;

    }

    public void eliminarAutor(String codigo){
        daoAutor.borrarAutor(codigo);
    }

    public void cerrarConexionBD(){
        daoAutor.cerrarConexionBD();
    }



}
