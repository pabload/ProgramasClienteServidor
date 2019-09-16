//version final//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    private String puerto;
    private Socket socket;
    private ServerSocket serverSocket;

    public Servidor(String puerto) {
        this.puerto = puerto;
        this.socket = null;
        this.serverSocket = null;
    }

    public void CrearServidor() {
        try {
            serverSocket = new ServerSocket(Integer.parseInt(puerto));
            socket = serverSocket.accept();
            ConfigurarServidor();
        } catch (Exception e) {
            System.out.println("Error al crear socket del servidor " + e);
        }
    }

    public void ConfigurarServidor() {
        BufferedReader lector;
        try {
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String entrada;
            Scanner scanner = new Scanner(System.in);
            String salida;
            PrintWriter escritor;
            try {
                escritor = new PrintWriter(socket.getOutputStream(), true);
                do {
                    entrada = lector.readLine();

                    System.out.println(entrada);
                    if (entrada.equalsIgnoreCase("fin")) {
                        cerrarSockets();
                    }
                    salida = scanner.nextLine();
                    escritor.println(salida);
                } while (!entrada.equalsIgnoreCase("fin"));
            } catch (IOException ex) {
                System.out.println("Erro al crear escritor " + ex);
            }
        } catch (IOException ex) {
            System.out.println("Error al crear lector " + ex);
        }

    }

    public void cerrarSockets() {
        try {
            socket.close();
            serverSocket.close();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error al cerrar sockets " + e);
        }
    }

}
