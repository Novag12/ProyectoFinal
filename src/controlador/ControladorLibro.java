/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package controlador;

import accesoDatos.DaoLibro;
import logica.Libro;
import logica.Usuario;

import java.sql.SQLException;
import java.util.List;

public class ControladorLibro {
     DaoLibro daoLibro;

    public ControladorLibro() {
        daoLibro = new DaoLibro();
    }

    public int agregarLibro(String ISBN, String titulo_libro, String anho_publicacion, String idioma_libro, String numero_paginas, String codigo_editorial ) {
        Libro lib = new Libro();
        lib.setISBN(ISBN);
        lib.setTituloLibro(titulo_libro);
        lib.setAnhoPublicacion(anho_publicacion);
        lib.setIdiomaLibro(idioma_libro);
        lib.setNumeroPaginas(numero_paginas);
        lib.setCodigoEditorial(codigo_editorial);

        int result = daoLibro.guardarLibro(lib);
        return result;

    }
    public List<Libro> listarLibro(){
        List lib = this.daoLibro.listarLibros();
        return lib;

    }

    public void modificarLibro(Libro lib, String codISBN) {

        daoLibro.modificarLibro(lib, codISBN);
    }


    public void borrarLibro(String ISBN) {
        try {
            daoLibro.borrarLibro(ISBN);
            System.out.println("Libro borrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al borrar el libro: " + e.getMessage());
        }
    }

    public Libro consultarLibro(String ISBN) {
        try {
            return daoLibro.consultarLibro(ISBN);
        } catch (SQLException e) {
            System.out.println("Error al consultar el libro: " + e.getMessage());
            return null;
        }
    }
}
