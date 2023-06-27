/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/

package GUI;

import controlador.ControladorUsuario;
import logica.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {
    private JTextField userName;
    private JPasswordField password;
    private JButton iniciarSesiónButton;
    private JPanel panel1;
    private JLabel Label1;

    public static String nombreUsuario = "";
    public static String tipoUsuario = "";

    ControladorUsuario controladorUsuario;

    public Login(){
        super("Registro");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        controladorUsuario = new ControladorUsuario();
         ImageIcon imageIcon = new ImageIcon(getClass().getResource("/login.png"));
         Label1.setIcon(imageIcon);
         Label1.setHorizontalAlignment(JLabel.CENTER);
         Label1.setHorizontalAlignment(JLabel.CENTER);

        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = userName.getText();
                String pass =  String.valueOf(password.getPassword());
                try{
                    Usuario user = controladorUsuario.consultarUsuario(pass);
                    if(user != null){
                        System.out.println(user.getDireccion());
                        System.out.println(user.getNombreUsuario());
                        if(user.getNombreUsuario().equals(nombre)){
                            JOptionPane.showMessageDialog(null,"Bienvenido");
                            nombreUsuario = userName.getText();
                            tipoUsuario = user.getTipoUsuario();
                            VentanaInicio inicio = new VentanaInicio();
                            inicio.setVisible(true);
                            dispose();
                        } else JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");
                    }
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"El Usuario: " + nombre + " no existe en la BD");
                    System.out.println("Error al consultar la base de datos");

                }
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Login();
                frame.setSize(350, 350);
                frame .setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        int opc = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Mensaje de confirmación", JOptionPane.OK_CANCEL_OPTION);
                        if (opc == 0) {
                            System.exit(0);
                        }
                    }
                });

            }
        });

    }
}
