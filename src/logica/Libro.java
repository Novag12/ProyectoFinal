/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package logica;

public class
Libro {
    private String ISBN;
    private String tituloLibro;
    private String anhoPublicacion;
    private String idiomaLibro;
    private String numeroPaginas;
    private String codigoEditorial;

    // Constructor, getters y setters


    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getAnhoPublicacion() {
        return anhoPublicacion;
    }

    public void setAnhoPublicacion(String anhoPublicacion) {
        this.anhoPublicacion = anhoPublicacion;
    }

    public String getIdiomaLibro() {
        return idiomaLibro;
    }

    public void setIdiomaLibro(String idiomaLibro) {
        this.idiomaLibro = idiomaLibro;
    }

    public String getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(String numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getCodigoEditorial() {
        return codigoEditorial;
    }

    public void setCodigoEditorial(String codigoEditorial) {
        this.codigoEditorial = codigoEditorial;
    }
}
