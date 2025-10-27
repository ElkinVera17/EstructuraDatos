package controlador;

import vista.*;
import modelo.*;

import javax.swing.*;
import java.awt.event.*;

public class ControladorRopa implements ActionListener {

    private VistaPrincipal vista;
    private Lista listaRopa;

    public ControladorRopa(VistaPrincipal vista, Lista lista) {
        this.vista = vista;
        this.listaRopa = lista;

        // Escuchar botones del menú
        vista.getBtnIrCarrito().addActionListener(this);
        vista.getBtnIrFormulario().addActionListener(this);
        vista.getBtnSalir().addActionListener(this);

        // Escuchar botones del formulario
        vista.getPanelFormulario().getBtnGuardarInicio().addActionListener(this);
        vista.getPanelFormulario().getBtnGuardarFinal().addActionListener(this);
        vista.getPanelFormulario().getBtnCancelar().addActionListener(this);
        vista.getPanelFormulario().getBtnRegresarMenu().addActionListener(this);

        // Escuchar botones del carrito
        vista.getPanelCarrito().getBtnRegresar().addActionListener(this);
        vista.getPanelCarrito().getBtnEliminarInicio().addActionListener(this);
        vista.getPanelCarrito().getBtnEliminarFinal().addActionListener(this);
        vista.getPanelCarrito().getBtnBuscar().addActionListener(this);
        vista.getPanelCarrito().getBtnMostrar().addActionListener(this);
        vista.getPanelCarrito().getBtnContar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == vista.getBtnIrFormulario()) {
            vista.getCardLayout().show(vista.getPanelContenedor(), "formulario");
        } 
        else if (src == vista.getBtnIrCarrito()) {
            vista.getCardLayout().show(vista.getPanelContenedor(), "carrito");
        } 
        else if (src == vista.getBtnSalir()) {
            System.exit(0);
        } 
        else if (src == vista.getPanelFormulario().getBtnGuardarInicio()) {
            agregarRopa(true); // true = al inicio
        }
        else if (src == vista.getPanelFormulario().getBtnGuardarFinal()) {
            agregarRopa(false); // false = al final
        }
        else if (src == vista.getPanelFormulario().getBtnCancelar()) {
            limpiarFormulario();
            vista.getCardLayout().show(vista.getPanelContenedor(), "menu");
        }
        else if (src == vista.getPanelFormulario().getBtnRegresarMenu() || 
                 src == vista.getPanelCarrito().getBtnRegresar()) {
            vista.getCardLayout().show(vista.getPanelContenedor(), "menu");
        }
        // Operaciones de lista en el carrito
        else if (src == vista.getPanelCarrito().getBtnEliminarInicio()) {
            eliminarInicio();
        }
        else if (src == vista.getPanelCarrito().getBtnEliminarFinal()) {
            eliminarFinal();
        }
        else if (src == vista.getPanelCarrito().getBtnBuscar()) {
            buscarElemento();
        }
        else if (src == vista.getPanelCarrito().getBtnMostrar()) {
            mostrarElementos();
        }
        else if (src == vista.getPanelCarrito().getBtnContar()) {
            contarElementos();
        }
    }

    private void agregarRopa(boolean alInicio) {
        try {
            // Validar y obtener datos
            String color = vista.getPanelFormulario().getTxtColor().getText().trim();
            if (color.isEmpty()) {
                throw new IllegalArgumentException("El color no puede estar vacío");
            }

            String codigoStr = vista.getPanelFormulario().getTxtCodigo().getText().trim();
            if (codigoStr.isEmpty()) {
                throw new IllegalArgumentException("El código no puede estar vacío");
            }
            int codigo = Integer.parseInt(codigoStr);

            int talla = (int) vista.getPanelFormulario().getCbTalla().getSelectedItem();

            String precioStr = vista.getPanelFormulario().getTxtPrecio().getText().trim();
            if (precioStr.isEmpty()) {
                throw new IllegalArgumentException("El precio no puede estar vacío");
            }
            double precio = Double.parseDouble(precioStr);

            String tipo = vista.getPanelFormulario().getCbTipo().getSelectedItem().toString();

            // Crear y validar objeto Ropa
            Ropa ropa = new Ropa(color, codigo, talla, precio, tipo);

            // Insertar en la lista según la opción
            if (alInicio) {
                listaRopa.insertarInicio(ropa);
                // Agregar al inicio de la tabla del carrito
                vista.getPanelCarrito().agregarFilaAlInicio(
                    ropa.getTipo(), 
                    ropa.getPrecio(), 
                    ropa.getTalla(), 
                    ropa.getCodigo(), 
                    ropa.getColor()
                );
            } else {
                listaRopa.insertarFinal(ropa);
                // Agregar al final de la tabla del carrito
                vista.getPanelCarrito().agregarFilaAlFinal(
                    ropa.getTipo(), 
                    ropa.getPrecio(), 
                    ropa.getTalla(), 
                    ropa.getCodigo(), 
                    ropa.getColor()
                );
            }

            JOptionPane.showMessageDialog(vista, 
                "Prenda agregada " + (alInicio ? "al inicio" : "al final") + " con éxito", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
            limpiarFormulario();
            vista.getCardLayout().show(vista.getPanelContenedor(), "menu");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, 
                "Error en formato numérico:\n- Código debe ser un número entero\n- Precio debe ser un número válido", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, 
                " Error de validación:\n" + ex.getMessage(), 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, 
                " Error inesperado:\n" + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        vista.getPanelFormulario().getTxtColor().setText("");
        vista.getPanelFormulario().getTxtCodigo().setText("");
        vista.getPanelFormulario().getTxtPrecio().setText("");
        vista.getPanelFormulario().getCbTalla().setSelectedIndex(0);
        vista.getPanelFormulario().getCbTipo().setSelectedIndex(0);
    }

    // Métodos para operaciones de lista en el carrito
    private void eliminarInicio() {
        try {
            if (listaRopa.esVacia()) {
                throw new IllegalArgumentException("El carrito está vacío");
            }
            
            // Eliminar de la lista
            Nodo eliminado = listaRopa.eliminarAlInicio();
            
            // Eliminar de la tabla
            vista.getPanelCarrito().eliminarPrimeraFila();
            
            JOptionPane.showMessageDialog(vista, 
                " Elemento eliminado del inicio:\n" + eliminado.getDato(), 
                "Eliminado", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFinal() {
        try {
            if (listaRopa.esVacia()) {
                throw new IllegalArgumentException("El carrito está vacío");
            }
            
            // Eliminar de la lista
            Nodo eliminado = listaRopa.eliminarAlFinal();
            
            // Eliminar de la tabla
            vista.getPanelCarrito().eliminarUltimaFila();
            
            JOptionPane.showMessageDialog(vista, 
                " Elemento eliminado del final:\n" + eliminado.getDato(), 
                "Eliminado", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarElemento() {
        try {
            String codigoStr = vista.getPanelCarrito().getTxtBuscar().getText().trim();
            
            // Buscar en la tabla
            boolean encontrado = vista.getPanelCarrito().buscarPorCodigo(codigoStr);
            
            if (encontrado) {
                JOptionPane.showMessageDialog(vista, 
                    " Elemento con código " + codigoStr + " encontrado", 
                    "Búsqueda Exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, 
                    " Elemento con código " + codigoStr + " no encontrado", 
                    "Búsqueda", 
                    JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarElementos() {
        try {
            vista.getPanelCarrito().mostrarInformacionLista();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void contarElementos() {
        try {
            int cantidad = listaRopa.contarElementos();
            JOptionPane.showMessageDialog(vista, 
                " Total de elementos en el carrito: " + cantidad, 
                "Conteo", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}