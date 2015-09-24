package cliente;

/**
 *
 * @author Ram
 */
public class Movimiento {
    private double cantidad;
    private char tipo;
    private int cuenta;

    public Movimiento() {
    }

    public Movimiento(double cantidad, char tipo, int cuenta) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.cuenta = cuenta;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }
    
    
}
