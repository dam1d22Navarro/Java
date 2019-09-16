//Servidor recibe 2 números del cliente y el servidor devuelve la suma.

import java.io.*;
import java.net.*;
public class Servidor {

    private static final int PORT = 4450;

    public static void main(String[] args) throws IOException {

        // Establece el puerto en el que escucha peticiones
        ServerSocket socketServidor = null;
        try {
            socketServidor = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("No puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }

        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        while(true) {
            // Se bloquea hasta que recibe alguna petición de un cliente
            // abriendo un socket para el cliente
            System.out.println(" Servidor iniciado " );
           try {
               socketCliente = socketServidor.accept();

               // Establece canal de entrada
               entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
               // Establece canal de salida
               salida = new PrintWriter(new BufferedWriter(new
                       OutputStreamWriter(socketCliente.getOutputStream())), true);

               salida.println("Introduzca el primer número: ");
               int primernum = Integer.parseInt(entrada.readLine());

               String ope1=String.valueOf(primernum);
               System.out.println("El primer usuario ha introducido "+ope1 );

               salida.println("Introduzca el segundo número: ");
               int segundonum = Integer.parseInt(entrada.readLine());

               String ope2=String.valueOf(segundonum);
               System.out.println("El segundo usuario ha introducido "+ope2 );

               salida.println(primernum + segundonum);

              

           }catch (Exception e){

               e.printStackTrace();
           }
            salida.close();
            entrada.close();
            socketCliente.close();
        }



    }
}
