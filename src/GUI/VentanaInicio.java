/*BASE DE DATOS DE LA BIBLIOTECA
   Autores:
            Andres Felipe Lopez Rodriguez: 2128542
            Mike García Guzmán: 2181021
*/


package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame {

    private JPanel Inicio;
    private JButton usuariosButton;
    private JButton librosButton;
    private JButton prestamoBTN;
    private JButton solicitarLibroButton;
    private JButton MultasBTN;

    public VentanaInicio() {
        super("Sistema Bibliotecario");
        setSize(550, 400);
        setContentPane(Inicio);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Establecer estilo y colores de los botones
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonTextColor = Color.WHITE;
        Color buttonBackgroundColor = Color.DARK_GRAY;
        Color buttonHoverColor = Color.GRAY;
        Color buttonBorderColor = Color.LIGHT_GRAY;

        solicitarLibroButton.setFont(buttonFont);
        solicitarLibroButton.setForeground(buttonTextColor);
        solicitarLibroButton.setBackground(buttonBackgroundColor);
        solicitarLibroButton.setOpaque(true);
        solicitarLibroButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(buttonBorderColor, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        solicitarLibroButton.setFocusPainted(false);

        prestamoBTN.setFont(buttonFont);
        prestamoBTN.setForeground(buttonTextColor);
        prestamoBTN.setBackground(buttonBackgroundColor);
        prestamoBTN.setOpaque(true);
        prestamoBTN.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(buttonBorderColor, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        prestamoBTN.setFocusPainted(false);

        usuariosButton.setFont(buttonFont);
        usuariosButton.setForeground(buttonTextColor);
        usuariosButton.setBackground(buttonBackgroundColor);
        usuariosButton.setOpaque(true);
        usuariosButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(buttonBorderColor, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        usuariosButton.setFocusPainted(false);

        librosButton.setFont(buttonFont);
        librosButton.setForeground(buttonTextColor);
        librosButton.setBackground(buttonBackgroundColor);
        librosButton.setOpaque(true);
        librosButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(buttonBorderColor, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        librosButton.setFocusPainted(false);

        MultasBTN.setFont(buttonFont);
        MultasBTN.setForeground(buttonTextColor);
        MultasBTN.setBackground(buttonBackgroundColor);
        MultasBTN.setOpaque(true);
        MultasBTN.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(buttonBorderColor, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        MultasBTN.setFocusPainted(false);

        // Agregar efecto hover a los botones
        prestamoBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prestamoBTN.setBackground(buttonHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                prestamoBTN.setBackground(buttonBackgroundColor);
            }
        });

        solicitarLibroButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                solicitarLibroButton.setBackground(buttonHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                solicitarLibroButton.setBackground(buttonBackgroundColor);
            }
        });

        usuariosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usuariosButton.setBackground(buttonHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                usuariosButton.setBackground(buttonBackgroundColor);
            }
        });

        librosButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                librosButton.setBackground(buttonHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                librosButton.setBackground(buttonBackgroundColor);
            }
        });

        MultasBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MultasBTN.setBackground(buttonHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                MultasBTN.setBackground(buttonBackgroundColor);
            }
        });

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuarios usuarios = new Usuarios();
                usuarios.setVisible(true);
                dispose();
            }
        });

        librosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libros libros = new Libros();
                libros.setVisible(true);
                dispose();
            }
        });

        MultasBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Multas multas = new Multas();
                multas.setVisible(true);
                dispose();
            }
        });
    }
}
