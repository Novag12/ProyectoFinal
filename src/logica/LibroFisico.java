/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package logica;

public class LibroFisico {
    private String ISBN;
    private String nombreLibro;

    // Constructor, getters y setters

    public LibroFisico(String ISBN, String nombreLibro) {
        this.ISBN = ISBN;
        this.nombreLibro = nombreLibro;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }
}
