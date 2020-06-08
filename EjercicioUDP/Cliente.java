/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Albert
 */
public class Cliente {
    public static void main (String arg[]){
        final int puertoServidor = 5000;
        Scanner leer = new Scanner(System.in);
        String o = "";
        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();
            do{
                byte buffer[] = new byte[1024];
                byte buffer2[] = new byte[1024];
                System.out.println("Ingrese Frase: ");
                String mensaje = leer.nextLine();
                buffer = mensaje.getBytes();

                DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puertoServidor);
                socketUDP.send(pregunta);

                DatagramPacket peticion = new DatagramPacket(buffer2, buffer2.length);            
                socketUDP.receive(peticion);

                mensaje = new String(peticion.getData());            
                System.out.println("Cantidad de palabras: " + mensaje);
                System.out.println("¿Desea salir? (s/n): ");
                o = leer.nextLine();
            }while(!o.equals("s"));
            socketUDP.close();
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
