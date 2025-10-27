package vista;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class Formulario extends JPanel {

    // Campos del formulario
    private JTextField txtColor;
    private JTextField txtCodigo;
    private JComboBox<Integer> cbTalla;
    private JTextField txtPrecio;
    private JComboBox<String> cbTipo;
    private JButton btnGuardarInicio;
    private JButton btnGuardarFinal;
    private JButton btnCancelar;
    private JButton btnRegresarMenu;

    public Formulario() {
        setLayout(new GridBagLayout());
        setBackground(new Color(236, 240, 241));

        // ===== Crear componentes =====
        JLabel lblColor = new JLabel("Color:");
        txtColor = new JTextField(15);

        JLabel lblCodigo = new JLabel("Código:");
        txtCodigo = new JTextField(15);

        JLabel lblTalla = new JLabel("Talla:");
        cbTalla = new JComboBox<>();
        for (int i = 20; i <= 60; i += 2) { // Rango corregido según validación
            cbTalla.addItem(i);
        }

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(15);

        JLabel lblTipo = new JLabel("Tipo:");
        cbTipo = new JComboBox<>(new String[]{"Pantalon", "Camisa", "Shompa"});

        btnGuardarInicio = new JButton("Guardar al Inicio");
        btnGuardarInicio.setBackground(new Color(46, 204, 113));
        btnGuardarInicio.setForeground(Color.WHITE);
        
        btnGuardarFinal = new JButton("Guardar al Final");
        btnGuardarFinal.setBackground(new Color(52, 152, 219));
        btnGuardarFinal.setForeground(Color.WHITE);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);
        
        btnRegresarMenu = new JButton("Regresar al Menú");
        btnRegresarMenu.setBackground(new Color(149, 165, 166));
        btnRegresarMenu.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(lblColor, gbc);
        gbc.gridx = 1;
        add(txtColor, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblCodigo, gbc);
        gbc.gridx = 1;
        add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lblTalla, gbc);
        gbc.gridx = 1;
        add(cbTalla, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(lblPrecio, gbc);
        gbc.gridx = 1;
        add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(lblTipo, gbc);
        gbc.gridx = 1;
        add(cbTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(btnGuardarInicio, gbc);
        gbc.gridx = 1;
        add(btnGuardarFinal, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(btnCancelar, gbc);
        gbc.gridx = 1;
        add(btnRegresarMenu, gbc);
    }

    // ===== Getters para el controlador =====
    public JTextField getTxtColor() { 
    	return txtColor; 
    	}
    public JTextField getTxtCodigo() {
    	return txtCodigo; 
    	}
    public JComboBox<Integer> getCbTalla() {
    	return cbTalla; 
    	}
    public JTextField getTxtPrecio() {
    	return txtPrecio; 
    	}
    public JComboBox<String> getCbTipo() {
    	return cbTipo; 
    	}
    public JButton getBtnGuardarInicio() {
    	return btnGuardarInicio; 
    	}
    public JButton getBtnGuardarFinal() {
    	return btnGuardarFinal; 
    	}
    public JButton getBtnCancelar() {
    	return btnCancelar; 
    	}
    public JButton getBtnRegresarMenu() {
    	return btnRegresarMenu; 
    	}
}
