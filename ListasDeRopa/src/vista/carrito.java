package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class carrito extends JPanel {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JLabel lblPrecioTotal;
    private JLabel lblCantidadElementos;
    private JButton btnRegresar;
    private JButton btnEliminarInicio;
    private JButton btnEliminarFinal;
    private JButton btnBuscar;
    private JButton btnMostrar;
    private JButton btnContar;
    private JTextField txtBuscar;

    public carrito() {
        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241));

        // ===== Panel superior para operaciones =====
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.setBackground(new Color(236, 240, 241));
        
        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        panelBusqueda.setBackground(new Color(236, 240, 241));
        JLabel lblBuscar = new JLabel("Buscar por Código:");
        txtBuscar = new JTextField(15);
        btnBuscar = new JButton("Buscar Elemento");
        btnBuscar.setBackground(new Color(52, 152, 219));
        btnBuscar.setForeground(Color.WHITE);
        
        panelBusqueda.add(lblBuscar);
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        // Panel de operaciones
        JPanel panelOperaciones = new JPanel(new FlowLayout());
        panelOperaciones.setBackground(new Color(236, 240, 241));
        
        btnEliminarInicio = new JButton("Eliminar Primero");
        btnEliminarInicio.setBackground(new Color(231, 76, 60));
        btnEliminarInicio.setForeground(Color.WHITE);
        
        btnEliminarFinal = new JButton("Eliminar Último");
        btnEliminarFinal.setBackground(new Color(231, 76, 60));
        btnEliminarFinal.setForeground(Color.WHITE);
        
        btnMostrar = new JButton("Mostrar Lista");
        btnMostrar.setBackground(new Color(155, 89, 182));
        btnMostrar.setForeground(Color.WHITE);
        
        btnContar = new JButton("Contar Elementos");
        btnContar.setBackground(new Color(241, 196, 15));
        btnContar.setForeground(Color.BLACK);
        
        panelOperaciones.add(btnEliminarInicio);
        panelOperaciones.add(btnEliminarFinal);
        panelOperaciones.add(btnMostrar);
        panelOperaciones.add(btnContar);

        panelSuperior.add(panelBusqueda);
        panelSuperior.add(panelOperaciones);

        // ===== Tabla =====
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Talla");
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Color");

        tabla = new JTable(modeloTabla);
        tabla.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tabla);

        // ===== Panel inferior para información y botón regresar =====
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(236, 240, 241));
        
        // Panel de información
        JPanel panelInfo = new JPanel(new FlowLayout());
        panelInfo.setBackground(new Color(236, 240, 241));
        lblPrecioTotal = new JLabel("Precio Total: $0.0");
        lblPrecioTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        lblCantidadElementos = new JLabel("Elementos: 0");
        lblCantidadElementos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        panelInfo.add(lblPrecioTotal);
        panelInfo.add(new JLabel(" | "));
        panelInfo.add(lblCantidadElementos);

        // Botón regresar
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(236, 240, 241));
        btnRegresar = new JButton("Regresar al Menú");
        btnRegresar.setBackground(new Color(149, 165, 166));
        btnRegresar.setForeground(Color.WHITE);
        panelBoton.add(btnRegresar);

        panelInferior.add(panelInfo, BorderLayout.WEST);
        panelInferior.add(panelBoton, BorderLayout.EAST);

        // Agregar todos los componentes
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // ===== Métodos para manipular la tabla =====
    public void agregarFilaAlInicio(String tipo, double precio, int talla, int codigo, String color) {
        modeloTabla.insertRow(0, new Object[]{tipo, precio, talla, codigo, color});
        actualizarPrecioTotal();
        actualizarCantidadElementos();
    }

    public void agregarFilaAlFinal(String tipo, double precio, int talla, int codigo, String color) {
        modeloTabla.addRow(new Object[]{tipo, precio, talla, codigo, color});
        actualizarPrecioTotal();
        actualizarCantidadElementos();
    }

    private void actualizarPrecioTotal() {
        double total = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            try {
                total += Double.parseDouble(modeloTabla.getValueAt(i, 1).toString());
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir precio en fila " + i);
            }
        }
        lblPrecioTotal.setText(String.format("Precio Total: $%.2f", total));
    }

    private void actualizarCantidadElementos() {
        int cantidad = modeloTabla.getRowCount();
        lblCantidadElementos.setText("Elementos: " + cantidad);
    }

    public void eliminarPrimeraFila() {
        if (modeloTabla.getRowCount() > 0) {
            modeloTabla.removeRow(0);
            actualizarPrecioTotal();
            actualizarCantidadElementos();
        }
    }

    public void eliminarUltimaFila() {
        int rowCount = modeloTabla.getRowCount();
        if (rowCount > 0) {
            modeloTabla.removeRow(rowCount - 1);
            actualizarPrecioTotal();
            actualizarCantidadElementos();
        }
    }

    public boolean buscarPorCodigo(String codigoStr) {
        if (codigoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un código para buscar", "Búsqueda", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int codigoBuscado = Integer.parseInt(codigoStr);
            boolean encontrado = false;
            
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                Object valorCodigo = modeloTabla.getValueAt(i, 3); // Columna de código (índice 3)
                if (valorCodigo != null) {
                    try {
                        int codigoEnTabla = Integer.parseInt(valorCodigo.toString());
                        if (codigoEnTabla == codigoBuscado) {
                            tabla.setRowSelectionInterval(i, i);
                            tabla.scrollRectToVisible(tabla.getCellRect(i, 0, true));
                            encontrado = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar filas con código inválido
                    }
                }
            }

            return encontrado;
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número válido", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void mostrarInformacionLista() {
        int cantidad = modeloTabla.getRowCount();
        double total = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            total += Double.parseDouble(modeloTabla.getValueAt(i, 1).toString());
        }

        String mensaje = "=== INFORMACIÓN DE LA LISTA ===\n" +
                        "Total de elementos: " + cantidad + "\n" +
                        "Precio total: $" + String.format("%.2f", total) + "\n" +
                        "Elementos en la lista:\n";

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            mensaje += (i + 1) + ". " + 
                      "Código: " + modeloTabla.getValueAt(i, 3) + " - " +
                      modeloTabla.getValueAt(i, 0) + " - $" + 
                      modeloTabla.getValueAt(i, 1) + " - Talla: " + 
                      modeloTabla.getValueAt(i, 2) + " - Color: " +
                      modeloTabla.getValueAt(i, 4) + "\n";
        }

        JOptionPane.showMessageDialog(this, mensaje, "Información de la Lista", JOptionPane.INFORMATION_MESSAGE);
    }

    // ===== Getters =====
    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JLabel getLblPrecioTotal() {
        return lblPrecioTotal;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JButton getBtnEliminarInicio() {
        return btnEliminarInicio;
    }

    public JButton getBtnEliminarFinal() {
        return btnEliminarFinal;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnMostrar() {
        return btnMostrar;
    }

    public JButton getBtnContar() {
        return btnContar;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JLabel getLblCantidadElementos() {
        return lblCantidadElementos;
    }
}
