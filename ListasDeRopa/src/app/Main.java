package app;
import modelo.*;
import vista.*;
import controlador.*;
public class Main {

	public static void main(String[] args) {
		Lista listaRopa = new Lista();
        VistaPrincipal vista = new VistaPrincipal();
        new ControladorRopa(vista, listaRopa);
        vista.setVisible(true);
	}

}
