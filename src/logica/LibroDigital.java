/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package logica;

public class LibroDigital {
    private String ISBN;
    private String URL;
    private String formatoArchivo;
    private String tamanhoArchivo;

    // Constructor, getters y setters

    public LibroDigital(String ISBN, String URL, String formatoArchivo, String tamanhoArchivo) {
        this.ISBN = ISBN;
        this.URL = URL;
        this.formatoArchivo = formatoArchivo;
        this.tamanhoArchivo = tamanhoArchivo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getFormatoArchivo() {
        return formatoArchivo;
    }

    public void setFormatoArchivo(String formatoArchivo) {
        this.formatoArchivo = formatoArchivo;
    }

    public String getTamanhoArchivo() {
        return tamanhoArchivo;
    }

    public void setTamanhoArchivo(String tamanhoArchivo) {
        this.tamanhoArchivo = tamanhoArchivo;
    }
}
