package Ejercicio;

import tcp.*;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static void main (String arg[]){        
        final String host = "127.0.0.1";
        final int puerto = 5000;
        DataInputStream respuesta;
        DataOutputStream peticion;
        Scanner leer = new Scanner(System.in);
        try {
            Socket socket = new Socket(host, puerto);
            
            respuesta = new DataInputStream(socket.getInputStream());
            peticion = new DataOutputStream(socket.getOutputStream());
            String op = "";
            while (true){
                System.out.println("\t\tMENU");
                System.out.println("\tOpcion 1");
                System.out.println("\tOpcion 2");
                System.out.println("\tOpcion 3");
                System.out.println("\tOpcion Salir");
                System.out.println("Elija una opcion: ");
                op = (leer.nextLine()).toUpperCase();
                if(op.equals("SALIR")){
                    break;
                }else{
                    peticion.writeUTF(op);
                    String mensaje = respuesta.readUTF();
                    System.out.println(mensaje);
                }
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
