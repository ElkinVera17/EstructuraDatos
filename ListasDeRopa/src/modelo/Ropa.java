package modelo;

public class Ropa {
	private String color;
    private int codigo;
    private int talla;
    private double precio;
    private String tipo;

    public Ropa(String color, int codigo, int talla, double precio, String tipo) {
        this.color = color;
        this.codigo = codigo;
        setTalla(talla);
        setPrecio(precio);
        setTipo(tipo);
    }

    // Getters y setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        if (talla < 20 || talla > 60) {
            throw new IllegalArgumentException("❌ La talla debe estar entre 20 y 60.");
        }
        this.talla = talla;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0 || precio > 300) {
            throw new IllegalArgumentException("❌ El precio debe estar entre 0 y 300.");
        }
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        tipo = tipo.toLowerCase();
        if (!(tipo.equals("pantalon") || tipo.equals("camisa") || tipo.equals("shompa"))) {
            throw new IllegalArgumentException("❌ El tipo debe ser: pantalon, camisa o shompa.");
        }
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Ropa{" +
                "color='" + color + '\'' +
                ", codigo=" + codigo +
                ", talla=" + talla +
                ", precio=" + precio +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
