package servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cliente.Movimiento;
import cliente.Cuenta;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Ram
 */
public class BD {

    Connection con;
    private static final String LOGIN = "select idClientes from Clientes where nombre=? and contrasenia=?";
    private static final String CUENTA = "select * from Clientes JOIN Cuenta on Clientes.idClientes=? and Cuenta.idClientes=?";
    private static final String BALANCE = "select * from Cuenta where idCuenta=?";
    private static final String Retirar = "Update Cuenta set balance=? where idCuenta=?";
    private static final String Registrar = "INSERT INTO operacionescuent (fechaOperacion, tipoMovimiento, cantidad, idCuenta) VALUES (?, ?, ?, ?);";

    public BD() {
    }

    private void conectar() {
        String usuario = "root";
        String clave = "ramponce";
        String urlBd = "jdbc:mysql://localhost:3306/bancodsd";
        String driverDb = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driverDb);
            con = DriverManager.getConnection(urlBd, usuario, clave);
        } catch (Exception e) {
            System.out.println("Mori conectandome");
        }
    }

    public Cuenta login(Movimiento m) {
        conectar();
        PreparedStatement ps, pscuenta = null;
        ResultSet rs;
        try {
            ps = con.prepareStatement(LOGIN);
            ps.setString(1, (String) m.getUsuario());
            ps.setString(2, (String) m.getClave());
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Encontre al Usuario");
                pscuenta = con.prepareStatement(CUENTA);
                pscuenta.setInt(1, rs.getInt("idClientes"));
                pscuenta.setInt(2, rs.getInt("idClientes"));
                rs = pscuenta.executeQuery();
                System.out.println("Buscando cuenta");
                rs.next();
                Cuenta c = new Cuenta();
                c.setIdCuenta(rs.getInt("idCuenta"));
                c.setBalance(rs.getDouble("balance"));
                return c;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Cuenta Retirar(Movimiento m) {
        conectar();
        PreparedStatement ps, psretirar = null;
        ResultSet rs;
        try {
            ps = con.prepareStatement(BALANCE);
            ps.setInt(1, m.getIdCuenta());
            rs = ps.executeQuery();
            if (rs.next()) {
                psretirar = con.prepareStatement(Retirar);
                psretirar.setDouble(1, rs.getDouble("balance") - m.getCantidad());
                psretirar.setInt(2, m.getIdCuenta());
                psretirar.executeUpdate();
                rs = ps.executeQuery();
                rs.next();
                Cuenta c = new Cuenta();
                c.setIdCuenta(rs.getInt("idCuenta"));
                c.setBalance(rs.getDouble("balance"));
                return c;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Cuenta Depositar(Movimiento m) {
        conectar();
        PreparedStatement ps, psretirar = null;
        ResultSet rs;
        try {
            ps = con.prepareStatement(BALANCE);
            ps.setInt(1, m.getIdCuenta());
            rs = ps.executeQuery();
            if (rs.next()) {
                psretirar = con.prepareStatement(Retirar);
                psretirar.setDouble(1, rs.getDouble("balance") + m.getCantidad());
                psretirar.setInt(2, m.getIdCuenta());
                psretirar.executeUpdate();
                rs = ps.executeQuery();
                rs.next();
                Cuenta c = new Cuenta();
                c.setIdCuenta(rs.getInt("idCuenta"));
                c.setBalance(rs.getDouble("balance"));
                return c;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Cuenta Balance(Movimiento m) {
        conectar();
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            ps = con.prepareStatement(BALANCE);
            ps.setInt(1, m.getIdCuenta());
            rs = ps.executeQuery();
            if (rs.next()) {
                Cuenta c = new Cuenta();
                c.setIdCuenta(rs.getInt("idCuenta"));
                c.setBalance(rs.getDouble("balance"));
                return c;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void RegistrarMovimiento(Movimiento m) {
        conectar();
        try
        {
        PreparedStatement ps = null;
        ps = con.prepareStatement(Registrar);
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        ps.setDate(1, date);
        ps.setString(2, "" + m.getTipo() );
        ps.setDouble(3, m.getCantidad());
        ps.setInt(4, m.getIdCuenta());
        ps.executeUpdate();
        } 
        catch(Exception e)
        {
            
        }
    }

}
