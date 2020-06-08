package Ejercicio;

import tcp.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    public static void main (String arg[]) throws SocketException{
        ServerSocket servidor = null;
        Socket socket = null;
        DataInputStream peticion;
        DataOutputStream respuesta;
        final int puerto = 5000;
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");
            System.out.println("\tClientes conectados");
            System.out.println("PuertoCliente\tDireccionIP");
            while(true){
                socket = servidor.accept();
                InetAddress ip = socket.getInetAddress();
                int pc = socket.getPort();
                peticion = new DataInputStream(socket.getInputStream());
                respuesta = new DataOutputStream(socket.getOutputStream());
                
                String opcion = peticion.readUTF();
                String res = Respuesta(opcion);
                System.out.println(pc + "\t\t" + ip.getHostAddress());
                
                respuesta.writeUTF(res);
                socket.close();
            }
        }catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String Respuesta(String x){
        switch (x.charAt(0)){
            case '1':
                return "Piedra";
            case '2':
                return "Papel";
            case '3':
                return "Tijera";
            default:
                return "Error";
        }
    }
}