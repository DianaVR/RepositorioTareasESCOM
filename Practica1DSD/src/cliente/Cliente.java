/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Nyappy
 */
public class Cliente {

    public static final int PUERTO = 1245;

    public Cliente() {
    }

    // Al hacer login me regresaras una cuenta 

    public Object login(Movimiento m) throws ClassNotFoundException {
        Object resultado;
        try {
            Socket cliente = new Socket("127.0.0.1", PUERTO);
            System.out.println("Cliente conectado");

            //Enviando los valores al Servidor
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.writeObject(m);

            //Recibiendo los valores del Servidor
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            resultado = ois.readObject();

            return resultado;
        } catch (IOException ex) {
            System.out.println("Conexion no exitosa");
            return null;

        }

    }

    public Object Servicio(Movimiento m) throws ClassNotFoundException {
        Object resultado = false;
        try {
            Socket cliente = new Socket("127.0.0.1", PUERTO);
            System.out.println("Cliente conectado");

            //Enviando los valores al Servidor
            ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
            salida.writeObject(m);

            //Recibiendo los valores del Servidor
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            resultado = ois.readObject();

            System.out.println(resultado);

        } catch (IOException ex) {
            System.out.println("Conexion no exitosa");

        }
        return resultado;
    }

}
