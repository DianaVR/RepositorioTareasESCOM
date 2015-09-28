package cliente;

import java.io.Serializable;

/**
 *
 * @author Ram
 */
public class Movimiento implements Serializable{

    private double cantidad=0;
    private char tipo;
    private int cuenta=0;
    private int idCuenta;
    private String usuario="";
    private String clave="";

    public Movimiento() {
    }
    
    //Este constructor sera para el login 

    public Movimiento(char tipo , String usuario, String clave) {
        this.tipo=tipo;
        this.usuario = usuario;
        this.clave = clave;
    }
    
    
    //Este constructor de usara para las operaciones normales
    public Movimiento(double cantidad, char tipo, int idCuenta) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.idCuenta = idCuenta;
    }
    
    
    //Este constructor de usara unicamente para la transferencia
    public Movimiento(double cantidad, char tipo, int idCuenta, int cuenta) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.cuenta = cuenta;
        this.idCuenta = idCuenta;
    }
    
    //Este constructor de usara unicamente para indicar el historial movimientos
    public Movimiento( char tipo, int idCuenta) {
        this.tipo = tipo;
        this.idCuenta = idCuenta;
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

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "cantidad=" + cantidad + ", tipo=" + tipo + ", cuenta=" + cuenta + ", idCuenta=" + idCuenta + ", usuario=" + usuario + ", clave=" + clave + '}';
    }
    
    
    

    

   

}
