/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package accesoDatos;

import logica.LibroDigital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoLibroDigital {
    private Connection connection;

    public DaoLibroDigital(Connection connection) {
        this.connection = connection;
    }

    public void agregarLibroDigital(LibroDigital libroDigital) throws SQLException {
        String query = "INSERT INTO LibroDigital (ISBN, URL, formato_archivo, tamanho_archivo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, libroDigital.getISBN());
            statement.setString(2, libroDigital.getURL());
            statement.setString(3, libroDigital.getFormatoArchivo());
            statement.setString(4, libroDigital.getTamanhoArchivo());

            statement.executeUpdate();
        }
    }

    public void actualizarLibroDigital(LibroDigital libroDigital) throws SQLException {
        String query = "UPDATE LibroDigital SET URL = ?, formato_archivo = ?, tamanho_archivo = ? WHERE ISBN = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, libroDigital.getURL());
            statement.setString(2, libroDigital.getFormatoArchivo());
            statement.setString(3, libroDigital.getTamanhoArchivo());
            statement.setString(4, libroDigital.getISBN());

            statement.executeUpdate();
        }
    }

    public void borrarLibroDigital(String ISBN) throws SQLException {
        String query = "DELETE FROM LibroDigital WHERE ISBN = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ISBN);

            statement.executeUpdate();
        }
    }

    public LibroDigital consultarLibroDigital(String ISBN) throws SQLException {
        String query = "SELECT * FROM LibroDigital WHERE ISBN = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ISBN);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new LibroDigital(
                            resultSet.getString("ISBN"),
                            resultSet.getString("URL"),
                            resultSet.getString("formato_archivo"),
                            resultSet.getString("tamanho_archivo")
                    );
                }
            }
        }

        return null; // Si no se encuentra el libro digital, retorna null
    }

    public List<LibroDigital> obtenerTodosLosLibrosDigitales() throws SQLException {
        List<LibroDigital> librosDigitales = new ArrayList<>();
        String query = "SELECT * FROM LibroDigital";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                LibroDigital libroDigital = new LibroDigital(
                        resultSet.getString("ISBN"),
                        resultSet.getString("URL"),
                        resultSet.getString("formato_archivo"),
                        resultSet.getString("tamanho_archivo")
                );

                librosDigitales.add(libroDigital);
            }
        }

        return librosDigitales;
    }
}
