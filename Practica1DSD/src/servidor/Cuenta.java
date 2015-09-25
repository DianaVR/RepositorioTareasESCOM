package servidor;

/**
 *
 * @author Ram
 */

//Se retornara esta objeto en caso que el login sea satisfactorio
public class Cuenta {
    
    private int idCuenta;
    private double balance;

    public Cuenta() {
    }
       
    
    public Cuenta(int idCuenta, double balance) {
        this.idCuenta = idCuenta;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    
    
    
}
