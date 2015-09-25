
package servidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static final int PUERTO = 1245;
    public static void main(String[] args){
        try {
            ServerSocket server = new ServerSocket(PUERTO);
            while(true){
                Socket cliente = server.accept();
                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
                Movimiento movPaquete = (Movimiento)ois.readObject();
                switch(movPaquete.getTipo()){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
            
        } catch (IOException ex) {
           System.out.println("No se pudo utilizar el puerto");
        } catch (ClassNotFoundException ex) {
           System.out.println("Error en el objeto recibido");
        }
        
        
    }
}
