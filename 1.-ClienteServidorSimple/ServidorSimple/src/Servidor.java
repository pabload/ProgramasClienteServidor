 //Version final//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
   
    private String puerto;
    private ServerSocket serverSocket;
    private Socket socket;

    public Servidor(String puerto) {
        this.puerto = puerto;
        this.serverSocket = null;
        this.socket = null;
    }

    public void IniciarServidor() throws IOException {
        try {
            try {
                serverSocket = new ServerSocket(Integer.parseInt(puerto));
                while (true) {
                    String Entrada;
                    socket = serverSocket.accept();
                    BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while ((Entrada = lector.readLine()) != null) {
                        System.out.println("me dijeron " + Entrada);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al recibir informacion " + e.toString());
            }

        } catch (Exception e) {
            System.out.println("Error al crear socket servidor " + e.toString());
        }
    }

}
