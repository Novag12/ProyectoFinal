/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/

package GUI;

import controlador.ControladorLibro;
import logica.Libro;
import logica.Usuario;
import controlador.ControladorLibroFisico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class Libros extends JFrame {
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


    ControladorLibro controladorLibro = new ControladorLibro();
    ControladorLibroFisico controladorLibroFisico= new ControladorLibroFisico();

    private static DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Libros(){
        super("Usuarios");
        setSize(600,600);
        setContentPane(panelUsuario);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        controladorLibro = new ControladorLibro();
        Tabla();
        listar();

        textNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!textNombre.getText().equals("")){
                    model.setRowCount(0);
                    for(Libro lib: controladorLibro.listarLibro()){
                        String cadena = lib.getTituloLibro();
                        int posicion = cadena.indexOf(textNombre.getText());
                        if(posicion != -1){
                            model.addRow(new Object[]{lib.getISBN(),lib.getTituloLibro(),lib.getAnhoPublicacion(),lib.getIdiomaLibro(),lib.getNumeroPaginas(),lib.getCodigoEditorial()});
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
                        controladorLibro.agregarLibro(textIdentificacion.getText(),textNombre.getText(),textDireccion.getText(),
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
                    Libro lib = controladorLibro.consultarLibro(textIdentificacion.getText());
                    if(lib != null){
                        textNombre.setText(lib.getISBN());
                        textDireccion.setText(lib.getTituloLibro());
                        textTelefono.setText(lib.getAnhoPublicacion());
                        textCorreo.setText(lib.getIdiomaLibro());
                        comboBoxTipoUsuario.setSelectedItem(lib.getCodigoEditorial());
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
                        Libro lib = controladorLibro.consultarLibro(String.valueOf(number));
                        if(lib != null){
                            lib.setISBN(textNombre.getText());
                            lib.setTituloLibro(textCorreo.getText());
                            lib.setAnhoPublicacion(textTelefono.getText());
                            lib.setIdiomaLibro(textDireccion.getText());
                            lib.setNumeroPaginas(comboBoxTipoUsuario.getSelectedItem().toString());
                            controladorLibro.modificarLibro(lib, textIdentificacion.getText() );
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
                    if(controladorLibro.consultarLibro(textIdentificacion.getText()) != null){
                        controladorLibro.borrarLibro(textIdentificacion.getText());
                        model.setRowCount(0);
                        listar();
                    } else JOptionPane.showMessageDialog(null, "El ISBN no existe en la base de datos");
                } else JOptionPane.showMessageDialog(null, "Ingrese un ISBN para poder realizar la operacion");
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
        List<Libro> libroList = controladorLibro.listarLibro();
        for(Libro libro: libroList){
            model.addRow(new Object[]{libro.getISBN(),libro.getTituloLibro(),libro.getAnhoPublicacion(),libro.getIdiomaLibro(), libro.getNumeroPaginas(),libro.getCodigoEditorial()});
        }
    }

    public void Tabla(){
        String[] titulo = new String[]{"ISBN","TITULO","AÑO PUBLICACIÓN","NUMERO PAGINAS","IDIOMA LIBRO","CÓDIGO EDITORIAL",};
        model.setColumnIdentifiers(titulo);
        table1.setModel(model);
    }


}
