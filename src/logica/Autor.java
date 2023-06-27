/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/
package logica;

public class Autor {
    String id_autor;
    String primer_apellido;
    String segundo_apellido;
    String primer_nombre;
    String segundo_nombre;

    public Autor(){}
    public String getCodigoAutor(){
        return id_autor;
    }
    public void setCodigoAutor(String id_autor){
        this.id_autor = id_autor;
    }
    public String getSurname(){
        return primer_apellido;
    }
    public void setSurname(String primer_apellido){this.primer_apellido = primer_apellido;}
    public String getSecondSurname(){ return segundo_apellido;}
    public void setSecondSurname(String segundo_apellido){this.segundo_apellido = segundo_apellido;}
    public String getFirstName(){
        return primer_nombre;
    }
    public void setFirstName(String primer_nombre){this.primer_nombre = primer_nombre;}
    public String getSecondName(){
        return segundo_nombre;
    }
    public void setSecondName(String segundo_nombre){this.segundo_nombre = segundo_nombre;}
}
