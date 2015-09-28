package servidor;

import cliente.Movimiento;
import cliente.Cuenta;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static final int PUERTO = 1245;

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(PUERTO);
            while (true) {
                Cuenta cuenta;
                BD bd = new BD();
                ObjectOutputStream salida;
                System.out.println("Esperando");

                Socket cliente = server.accept();

                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

                Movimiento movPaquete = (Movimiento) ois.readObject();

                switch (movPaquete.getTipo()) {
                    case '0':
                        cuenta = bd.Balance(movPaquete);
                        //Enviando los valores al Servidor
                        salida = new ObjectOutputStream(cliente.getOutputStream());
                        salida.writeObject(cuenta);
                        cliente.close();
                        break;
                    case '1':
                        cuenta = bd.Retirar(movPaquete);
                        bd.RegistrarMovimiento(movPaquete);
                        //Enviando los valores al Servidor
                        salida = new ObjectOutputStream(cliente.getOutputStream());
                        salida.writeObject(cuenta);
                        cliente.close();
                        break;
                    case '2':
                        cuenta = bd.Depositar(movPaquete);
                        bd.RegistrarMovimiento(movPaquete);
                        //Enviando los valores al Servidor
                        salida = new ObjectOutputStream(cliente.getOutputStream());
                        salida.writeObject(cuenta);
                        cliente.close();
                        break;
                    case '3':
                        cuenta = bd.Retirar(movPaquete);
                        Movimiento movPaquete2=movPaquete;
                        movPaquete2.setIdCuenta(movPaquete2.getCuenta());
                        if(bd.Depositar(movPaquete2)== null)
                        {
                            cuenta = bd.Depositar(movPaquete);
                        }
                        else
                        {
                            bd.RegistrarMovimiento(movPaquete);
                            bd.RegistrarMovimiento(movPaquete2);
                        }
                        //Enviando los valores al Servidor
                        salida = new ObjectOutputStream(cliente.getOutputStream());
                        salida.writeObject(cuenta);
                        cliente.close();
                        break;
                    case '4':
                        break;
                    case '5':
                        cuenta = bd.login(movPaquete);
                        //Enviando los valores al Servidor
                        salida = new ObjectOutputStream(cliente.getOutputStream());
                        salida.writeObject(cuenta);
                        cliente.close();
                        break;
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en el objeto recibido");
        }

    }
}
