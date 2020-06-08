package Ejercicio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Albert
 */
public class Servidor {
    public static void main (String arg[]){
        final int puerto = 5000;
        try {
            System.out.println("Servidor UDP iniciado");
            DatagramSocket socketUDP = new DatagramSocket(puerto);
            System.out.println("\tClientes conectados");
            System.out.println("PuertoCliente\tDireccionIP");
            while (true){
                byte buffer[] = new byte[1024];
                byte buffer2[] = new byte[1024];          
                
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                
                String cadena = new String(peticion.getData());
                cadena = "" + Contar(cadena);
                
                buffer2 = cadena.getBytes();
                
                DatagramPacket respuesta = new DatagramPacket(buffer2, buffer2.length, direccion, puertoCliente);
                socketUDP.send(respuesta);
                System.out.println(puertoCliente + "\t\t" + direccion.getHostAddress());
            }
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int Contar(String x){
        x = x.toLowerCase();
        int c = 0;
        String p = "";
        for(int i = 0; i < x.length(); i++){
            if(x.charAt(i) != ' '){
                p += x.charAt(i);
            }else{
                if(!p.equals("")){
                    c++;
                }
                p = "";
            }
        }
        if((int)p.charAt(0) != 0){
            c++;
        }
        return c;
    }
}