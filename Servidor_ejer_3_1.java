import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
	    Servidor miServidor = new Servidor();
	    miServidor.inicia();
    }

    private void inicia() throws IOException {
        ServerSocket conexion = new ServerSocket(8084);
        while(true) {
            Socket cliente = conexion.accept();
            HiloCliente hiloCliente = new HiloCliente(cliente);
            hiloCliente.start();
        }
    }
}
