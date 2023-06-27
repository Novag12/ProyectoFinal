/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/


package GUI;

import controlador.ControladorUsuario;
import logica.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class Usuarios extends JFrame {
    private JPanel panelUsuario;
    private JButton insertarButton;
    private JButton buscarButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JTable table1;
    private JTextField textIdentificacion;
    private JTextField textNombre;
    private JTextField textDireccion;
    private JTextField textTelefono;
    private JTextField textCorreo;
    private JComboBox comboBoxTipoUsuario;
    private JLabel Volver;
    private JButton profesorButton;
    private JButton estudianteButton;
    private JButton empleadoButton;

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    private static DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Usuarios(){
        super("Usuarios");
        setSize(600,600);
        setContentPane(panelUsuario);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        controladorUsuario = new ControladorUsuario();
        Tabla();
        listar();

        textNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!textNombre.getText().equals("")){
                    model.setRowCount(0);
                    for(Usuario user: controladorUsuario.listarUsuarios()){
                        String cadena = user.getNombreUsuario();
                        int posicion = cadena.indexOf(textNombre.getText());
                        if(posicion != -1){
                            model.addRow(new Object[]{user.getIdUsuario(),user.getNombreUsuario(),user.getDireccion(),user.getTelefono(),user.getEmail(),user.getTipoUsuario()});
                        }
                    }

                }else{
                    model.setRowCount(0);
                    listar();
                }

            }
        });

        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textIdentificacion.getText().equals("") && !textNombre.getText().equals("") && !textDireccion.getText().equals("") &&
                !textTelefono.getText().equals("") && !textCorreo.getText().equals("") && !comboBoxTipoUsuario.getSelectedItem().equals("")){
                    try{
                        int numbreId = Integer.parseInt(textIdentificacion.getText());
                        controladorUsuario.insertarUsuario(textIdentificacion.getText(),textNombre.getText(),textDireccion.getText(),
                                textTelefono.getText(),textCorreo.getText(),comboBoxTipoUsuario.getSelectedItem().toString());
                        model.setRowCount(0);
                        listar();
                        textIdentificacion.setText("");
                        textNombre.setText("");
                        textDireccion.setText("");
                        textTelefono.setText("");
                        textCorreo.setText("");
                        comboBoxTipoUsuario.setSelectedItem("");
                        textIdentificacion.requestFocus();
                    }catch(NumberFormatException exception){
                        JOptionPane.showMessageDialog(null,"La identificacion debe ser un numero con cant digitos = 10 ");
                    }
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textIdentificacion.getText().equals("")){
                    Usuario user = controladorUsuario.consultarUsuario(textIdentificacion.getText());
                    if(user != null){
                        textNombre.setText(user.getNombreUsuario());
                        textDireccion.setText(user.getDireccion());
                        textTelefono.setText(user.getTelefono());
                        textCorreo.setText(user.getEmail());
                        comboBoxTipoUsuario.setSelectedItem(user.getTipoUsuario());
                        model.setRowCount(0);
                        model.addRow(new Object[]{textIdentificacion.getText(),textNombre.getText(),textDireccion.getText(),textTelefono.getText(),
                        textCorreo.getText(),comboBoxTipoUsuario.getSelectedItem()});

                    } else JOptionPane.showMessageDialog(null,"El Usuario buscado no existe en la base de datos");
                } else JOptionPane.showMessageDialog(null,"Debes ingresar un ID para poder realizar la busqueda");
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textIdentificacion.getText().equals("") && !textNombre.getText().equals("") && !textCorreo.getText().equals("") &&
                !textTelefono.getText().equals("") && !textDireccion.getText().equals("") && !comboBoxTipoUsuario.getSelectedItem().equals("")){
                    try{
                        int number = Integer.parseInt(textIdentificacion.getText());
                        Usuario user = controladorUsuario.consultarUsuario(String.valueOf(number));
                        if(user != null){
                            user.setNombreUsuario(textNombre.getText());
                            user.setEmail(textCorreo.getText());
                            user.setTelefono(textTelefono.getText());
                            user.setDireccion(textDireccion.getText());
                            user.setTipoUsuario(comboBoxTipoUsuario.getSelectedItem().toString());
                            controladorUsuario.modificarUsuario(user, textIdentificacion.getText() );
                            model.setRowCount(0);
                            listar();
                            textIdentificacion.setText("");
                            textNombre.setText("");
                            textCorreo.setText("");
                            textTelefono.setText("");
                            textDireccion.setText("");
                            comboBoxTipoUsuario.setSelectedItem("");
                            textIdentificacion.requestFocus();
                        }
                    }catch (NumberFormatException exception){
                        JOptionPane.showMessageDialog(null,"La identificacion es un número");
                    }
                } else JOptionPane.showMessageDialog(null,"Ingrese un ID para editar");
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textIdentificacion.getText().equals("")){
                    if(controladorUsuario.consultarUsuario(textIdentificacion.getText()) != null){
                        controladorUsuario.eliminarUsuario(textIdentificacion.getText());
                        model.setRowCount(0);
                        listar();
                    } else JOptionPane.showMessageDialog(null, "La identificación no existe en la base de datos");
                } else JOptionPane.showMessageDialog(null, "Ingrese una identificación para poder realizar la operacion");
            }
        });

        Volver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VentanaInicio ventanaInicio = new VentanaInicio();
                ventanaInicio.setVisible(true);
                dispose();
            }
        });
    }

    public void listar(){
        List<Usuario> usuarioList = controladorUsuario.listarUsuarios();
        for(Usuario usuario: usuarioList){
            model.addRow(new Object[]{usuario.getIdUsuario(),usuario.getNombreUsuario(),usuario.getDireccion(),usuario.getTelefono(),usuario.getEmail(),usuario.getTipoUsuario()});
        }
    }

    public void Tabla(){
        String[] titulo = new String[]{"Identificación","Nombre","Dirección","Telefono","Correo","Categoria"};
        model.setColumnIdentifiers(titulo);
        table1.setModel(model);
    }


}
