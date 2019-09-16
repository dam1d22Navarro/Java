import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class HiloCliente extends Thread{

    private BufferedReader entrada;
    private PrintWriter salida;
    private Socket conexion;

    public HiloCliente(Socket conexion) throws IOException {
        this.conexion = conexion;
        salida = new PrintWriter(conexion.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
    }

    @Override
    public void run() {
        String mensaje = null;
        try {
            mensaje = entrada.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String partes[]= mensaje.split(" ");


        if(partes[1].equals("/")){
            salida.println("HTTP/1.1 200 OK");
            salida.println("");
            salida.println("<html><h1>Ejercicio4</h1></html>");
        }else if(partes[1].equals("/sensor")){
            String resultado = "";
            try {
                // Lanzamos la peticion al servidor UDP
                DatagramSocket socketUDP = new DatagramSocket();
                byte[] mensaje2 = "Mensaje a enviar".getBytes();
                InetAddress hostServidor = InetAddress.getByName("localhost");
                int puertoServidor = 6783;

                socketUDP.setSoTimeout(1);

                // Construimos un datagrama para enviar el mensaje al servidor
                DatagramPacket peticion = new DatagramPacket(mensaje2, mensaje2.length, hostServidor, puertoServidor);

                // Enviamos el datagrama
                socketUDP.send(peticion);


                // Leemos la respuesta

                // Construimos el DatagramPacket que contendrá la respuesta
                byte[] bufer = new byte[1000];
                DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
                socketUDP.receive(respuesta);


                resultado = new String(respuesta.getData());
                // Enviamos la respuesta del servidor a la salida estandar
                //System.out.println("Respuesta: " + new String(respuesta.getData()));
                socketUDP.close();

                // Cerramos el socket

            }catch ( SocketTimeoutException  e){
                resultado = "No se ha podido conectar con el servidor";
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            salida.println("HTTP/1.1 200 OK");
            salida.println("");
            // Este salida tiene que concatenar la temperatura que nos han devuelto
            salida.println("<html><h1>La temperatura es: "+resultado+
                    "</h1></html>");

        }else{
            salida.println("HTTP/1.1 404 NOT FOUND");
            salida.println("");
            salida.println("<html><h1>Error 404</h1></html>");
        }

        salida.close();
    }
}
