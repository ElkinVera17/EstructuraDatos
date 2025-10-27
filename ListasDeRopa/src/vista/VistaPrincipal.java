package vista;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {

    private JPanel panelContenedor;  // Aquí van todas las vistas (paneles)
    private JPanel menuPrincipal;
    private carrito panelCarrito;
    private Formulario panelFormulario;

    private JButton btnIrCarrito;
    private JButton btnIrFormulario;
    private JButton btnSalir;

    private CardLayout cardLayout;

    public VistaPrincipal() {
        setTitle("Sistema de Venta de Ropa");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);
        getContentPane().add(panelContenedor);

        // ==== Panel del menú principal ====
        menuPrincipal = new JPanel();
        menuPrincipal.setBackground(new Color(236, 240, 241));
        menuPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        JLabel lblTitulo = new JLabel("SISTEMA DE VENTA DE ROPA", SwingConstants.CENTER);
        lblTitulo.setBounds(190, 20, 400, 40);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        btnIrCarrito = new JButton("Abrir Carrito");
        btnIrCarrito.setBackground(new Color(52, 152, 219));
        btnIrCarrito.setForeground(Color.WHITE);
        btnIrCarrito.setBounds(214, 80, 248, 40);
        
        btnIrFormulario = new JButton("Agregar Prenda");
        btnIrFormulario.setBackground(new Color(46, 204, 113));
        btnIrFormulario.setForeground(Color.WHITE);
        btnIrFormulario.setBounds(214, 140, 248, 40);
        
        btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(231, 76, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBounds(214, 200, 248, 40);
        
        menuPrincipal.setLayout(null);
        menuPrincipal.add(lblTitulo);
        menuPrincipal.add(btnIrCarrito);
        menuPrincipal.add(btnIrFormulario);
        menuPrincipal.add(btnSalir);

        // ==== Otros paneles ====
        panelCarrito = new carrito();
        panelFormulario = new Formulario();

        // ==== Agregar paneles al contenedor ====
        panelContenedor.add(menuPrincipal, "menu");
        panelContenedor.add(panelCarrito, "carrito");
        panelContenedor.add(panelFormulario, "formulario");

        // Mostrar el menú al iniciar
        cardLayout.show(panelContenedor, "menu");
    }

    // Getters para el controlador
    public JButton getBtnIrCarrito() { return btnIrCarrito; }
    public JButton getBtnIrFormulario() { return btnIrFormulario; }
    public JButton getBtnSalir() { return btnSalir; }
    public carrito getPanelCarrito() { return panelCarrito; }
    public Formulario getPanelFormulario() { return panelFormulario; }
    public CardLayout getCardLayout() { return cardLayout; }
    public JPanel getPanelContenedor() { return panelContenedor; }
}
