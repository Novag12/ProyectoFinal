/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package controlador;

import accesoDatos.DaoMulta;
import logica.Libro;
import logica.Multa;
import logica.Usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ControladorMulta {
    private DaoMulta daoMulta;

    public ControladorMulta() {
        daoMulta = new DaoMulta();
    }



    public int insertarMulta(String id_usuario, String numero_prestamo, String fecha_multa, Integer valor, String descripcion){
        Multa mul = new Multa();
        mul.setIdUsuario(id_usuario);
        mul.setNumeroPrestamo(numero_prestamo);
        mul.setFechaMulta(LocalDate.parse(fecha_multa));
        mul.setValor(valor);
        mul.setDescripcion(descripcion);

        int result = daoMulta.guardarMulta(mul);
        return result;
    }

    public  Multa consultarMulta ( String numero_prestamo){
        Multa multa = daoMulta.consultarMulta(numero_prestamo);
        return multa;
    }

    public void modificarMulta(Multa mul, String numero_prestamo){ daoMulta.modificarMulta(mul, numero_prestamo);};
    public void borrarMulta(String numeroPrestamo) {
        daoMulta.borrarMulta(numeroPrestamo);
        System.out.println("Libro borrado exitosamente.");
    }


    public List<Multa> listarMulta() {
        List lib = this.daoMulta.listarMultas();
        return lib;

    }
}
