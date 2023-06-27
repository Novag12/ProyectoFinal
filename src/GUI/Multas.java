/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/


package GUI;

import logica.Libro;
import controlador.ControladorMulta;
import logica.Multa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class Multas extends JFrame {
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


    ControladorMulta controladorMulta = new ControladorMulta();
    private static DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public Multas(){
        super("Usuarios");
        setSize(600,600);
        setContentPane(panelUsuario);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        controladorMulta = new ControladorMulta();
        Tabla();
        listar();

        textNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!textNombre.getText().equals("")){
                    model.setRowCount(0);
                    for(Multa Mul: controladorMulta.listarMulta()){
                        String cadena = Mul.getNumeroPrestamo();
                        int posicion = cadena.indexOf(textNombre.getText());
                        if(posicion != -1){
                            model.addRow(new Object[]{Mul.getIdUsuario(),Mul.getNumeroPrestamo(),Mul.getFechaMulta(),Mul.getValor(),Mul.getDescripcion()});
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
                        !textTelefono.getText().equals("")  && !comboBoxTipoUsuario.getSelectedItem().equals("")){
                    try{
                        int numbreId = Integer.parseInt(textIdentificacion.getText());
                        controladorMulta.insertarMulta(textIdentificacion.getText(),textNombre.getText(),textDireccion.getText(), Integer.valueOf(textTelefono.getText()),comboBoxTipoUsuario.getSelectedItem().toString());
                        model.setRowCount(0);
                        listar();
                        textIdentificacion.setText("");
                        textNombre.setText("");
                        textDireccion.setText("");
                        textTelefono.setText("");
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
                    Multa mul = controladorMulta.consultarMulta(textIdentificacion.getText());
                    if(mul != null){
                        textIdentificacion.setText(mul.getIdUsuario());
                        textNombre.setText(mul.getNumeroPrestamo());
                        textDireccion.setText(String.valueOf(mul.getFechaMulta()));
                        textTelefono.setText(String.valueOf(mul.getValor()));
                        comboBoxTipoUsuario.setSelectedItem(mul.getDescripcion());
                        model.setRowCount(0);
                        model.addRow(new Object[]{textIdentificacion.getText(),textNombre.getText(),textDireccion.getText(),textTelefono.getText(),
                                comboBoxTipoUsuario.getSelectedItem()});

                    } else JOptionPane.showMessageDialog(null,"El Usuario buscado no existe en la base de datos");
                } else JOptionPane.showMessageDialog(null,"Debes ingresar un ID para poder realizar la busqueda");
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textIdentificacion.getText().equals("") && !textNombre.getText().equals("")  &&
                        !textTelefono.getText().equals("") && !textDireccion.getText().equals("") && !comboBoxTipoUsuario.getSelectedItem().equals("")){
                    try{
                        int number = Integer.parseInt(textIdentificacion.getText());
                        Multa mul = controladorMulta.consultarMulta(String.valueOf(number));
                        if(mul != null){
                            mul.setIdUsuario(textIdentificacion.getText());
                            mul.setNumeroPrestamo(textNombre.getText());
                            mul.setFechaMulta(LocalDate.parse(textDireccion.getText()));
                            mul.setValor(Integer.parseInt(textTelefono.getText()));
                            mul.setDescripcion(comboBoxTipoUsuario.getSelectedItem().toString());

                            controladorMulta.modificarMulta(mul, textNombre.getText() );
                            model.setRowCount(0);
                            listar();
                            textIdentificacion.setText("");
                            textNombre.setText("");
                            textDireccion.setText("");
                            textTelefono.setText("");
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
                    if(controladorMulta.consultarMulta(textIdentificacion.getText()) != null){
                        controladorMulta.borrarMulta(textIdentificacion.getText());
                        model.setRowCount(0);
                        listar();
                    } else JOptionPane.showMessageDialog(null, "La multa no existe en la base de datos");
                } else JOptionPane.showMessageDialog(null, "Ingrese un numero de prestamo para poder realizar la operacion");
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
        List<Multa> multaList = controladorMulta.listarMulta();
        for(Multa Mul: multaList){
            model.addRow(new Object[]{Mul.getIdUsuario(),Mul.getNumeroPrestamo(),Mul.getFechaMulta(),Mul.getValor(),Mul.getDescripcion()});
        }
    }

    public void Tabla(){
        String[] titulo = new String[]{"ID usuario","Número Prestamo","Fecha Multa","Valor Multa","Descripción"};
        model.setColumnIdentifiers(titulo);
        table1.setModel(model);
    }


}
