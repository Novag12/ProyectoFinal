/*BASE DE DATOS DE LA BIBLIOTECA
 Diseño e implementación del patrón DAO
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package accesoDatos;

import GUI.Multas;
import logica.Libro;
import logica.Multa;
import logica.Usuario;

import javax.swing.*;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoMulta {
    FachadaBD fachada;

    public  DaoMulta(){ fachada= new FachadaBD();}


    public void modificarMulta(Multa mul, String numero_prestamo){
        String sql_update = "UPDATE Multa SET numero_prestamo = '"+ mul.getNumeroPrestamo()+ "', id_usuario = '"+ mul.getIdUsuario()+ "', fecha_multa = '"+ mul.getFechaMulta()+ "', valor = '"+ mul.getValor() + "', descripcion = '" +mul.getDescripcion()+"'";
        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            if (sentencia.executeUpdate(sql_update) > 0) JOptionPane.showMessageDialog(null,"Se actualizó correctamente");
        }
        catch(SQLException e){ System.out.println(e);}
        catch(Exception e){ System.out.println(e);}
    }
    public int guardarMulta(Multa mul){
        String sql_guardar;
        sql_guardar = "INSERT INTO Multa(id_usuario, numero_prestamo, fecha_multa, valor, descripcion) VALUES ('" +
                mul.getIdUsuario() + "' , '" + mul.getNumeroPrestamo() + "' , '" + mul.getFechaMulta() + "' , '" + mul.getValor() + "' , '" +
                mul.getDescripcion()  + "')";

        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            int numFilas = sentencia.executeUpdate(sql_guardar);
            conn.close();
            return numFilas;
        }
        catch (SQLException e){
            System.out.println(e);
        }
        catch (Exception e){System.out.println(e);}
        return -1;
    }



    private int obtenerDiasRetraso(Date fechaDevolucion, Date fechaActual) {
        LocalDate fechaDevolucionLocal = fechaDevolucion.toLocalDate();
        LocalDate fechaActualLocal = fechaActual.toLocalDate();
        return (int) Duration.between(fechaDevolucionLocal.atStartOfDay(), fechaActualLocal.atStartOfDay()).toDays();
    }

    private int calcularValorMulta(int diasRetraso) {
        return 1200 * diasRetraso;
    }

    private String generarDescripcionMulta(int diasRetraso) {
        return "Retraso en la devolución de los libros. Días de retraso: " + diasRetraso;
    }

    public void borrarMulta(String numeroMulta){
        String sql_delete;
        sql_delete = "DELETE FROM Multa WHERE numero_prestamo ='" + numeroMulta +  "'";

        try{
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            int filasafectadas = sentencia.executeUpdate(sql_delete);
            if(filasafectadas > 0) JOptionPane.showMessageDialog(null, "El registro se ha eliminado correctamente");
            else {System.out.println("El " + numeroMulta + "no existe en la base de datos");}
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
    }

    public Multa consultarMulta(String numero_prestamo){
        Multa Mul = new Multa();
        String sql_select;
        sql_select= "SELECT id_usuario, numero_prestamo,fecha_multa, valor, descripcion = " + numero_prestamo;

        try {
            Connection conn = fachada.openConnection();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()) {
                Mul.setIdUsuario(tabla.getString(1));
                Mul.setNumeroPrestamo(tabla.getString(2));
                Mul.setFechaMulta(LocalDate.parse(tabla.getString(3)));
                Mul.setValor(Integer.parseInt(tabla.getString(4)));
                Mul.setDescripcion(tabla.getString(5));
            }
            conn.close();
            System.out.println("Conexion Cerrada");
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Mul;
    }

    public List listarMultas(){
        List<Multa> multas = new ArrayList<>();
        PreparedStatement sentencia = null;
        String sql_list = "SELECT * FROM Multa";

        try{
            Connection conn = fachada.openConnection();
            sentencia = conn.prepareStatement(sql_list);
            ResultSet rs = sentencia.executeQuery();
            while(rs.next()){
                Multa multa = new Multa();
                multa.setIdUsuario(rs.getString(1));
                multa.setNumeroPrestamo(rs.getString(2));
                multa.setFechaMulta(LocalDate.parse(rs.getString(3)));
                multa.setValor(Integer.parseInt(rs.getString(4)));
                multa.setDescripcion(rs.getString(5));
                multas.add(multa);
            }
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }

        return multas;

    }






}
