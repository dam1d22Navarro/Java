import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args)  throws IOException {
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;


        try {
            socketCliente = new Socket("localhost", 4450);
            // Obtenemos el canal de entrada


            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            // Obtenemos el canal de salida
            //System.out.println(entrada);
            salida = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(socketCliente.getOutputStream())),true);
            //System.out.println(salida);
        } catch (IOException e) {
            //System.err.println("No puede establer canales de E/S para la conexión");
            System.exit(-1);
        }

        Scanner scanner = new Scanner(System.in);
        String linea;


        try {
            // Muestro el primer mensaje del servidor
            System.out.println(entrada.readLine());
            // Recojo el primer numero del teclado y lo mando al servidor
            linea = scanner.nextLine();
            salida.println(linea);
            // Muestro el segundo mensaje del servidor
            System.out.println(entrada.readLine());
            // Recojo el segundo numero y lo mando al servidor
            linea = scanner.nextLine();
            salida.println(linea);
            // Muestro el resultado
            System.out.println("Resultado: "+entrada.readLine());






        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        // Libera recursos(flujos de entrada y salida)
        salida.close();
        entrada.close();
        socketCliente.close();
    }
}
