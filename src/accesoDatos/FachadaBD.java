/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FachadaBD {
    String url, user, password;
    Connection conexion  = null;

    FachadaBD(){
        url = "jdbc:postgresql://localhost:5432/33333";
        user = "postgres";
        password = "mike1243";
    }

    public Connection openConnection(){
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("No se pudo cargar el driver");
        }

        try{
            conexion = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion exitosa con la Bade de Datos");
            return conexion;

        }catch (SQLException e){
            System.out.println("No se pudo abrir la Base de Datos");
            return null;
        }
    }

    public Connection getConexion(){
        if(conexion == null) return this.openConnection();
        else return conexion;
    }

    public void closeConection (Connection c){
        try{
            if (conexion != null) c.close();
        }catch (SQLException e) {
            System.out.println("No se pudo cerrar la conexion");
        }
    }

    public static void main(String[] args){
        FachadaBD fachada = new FachadaBD();
        Connection c = fachada.openConnection();
        fachada.closeConection(c);
    }
}
