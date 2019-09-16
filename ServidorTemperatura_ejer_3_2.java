/*
   Este servidor devuelve una temperatura al servidor TCP y entonces en cualquier navegador introducimos el puerto del servidor TCP
   y tenemos que visualizar en el navegador ese valor.
*/

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class serverTemperatura {
        public static void main (String args[]){

            try {
                DatagramSocket socketUDP = new DatagramSocket(6783);
                byte[] bufer = new byte[1000];

                while (true) {
                    // Construimos el DatagramPacket para recibir peticiones
                    DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

                    // Leemos una petición del DatagramSocket
                    socketUDP.receive(peticion);



                    Random ran = new Random();
                    int x = ran.nextInt(16) + 5;
                    String resultado = String.valueOf(x);


                    // Construimos el DatagramPacket para enviar la respuesta
                    DatagramPacket respuesta = new DatagramPacket(resultado.getBytes(), resultado.length(),
                            peticion.getAddress(), peticion.getPort());

                    // Enviamos la respuesta, que es un eco
                    socketUDP.send(respuesta);
                }

            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }

}


