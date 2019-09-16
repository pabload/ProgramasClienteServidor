//version final//
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    public void crearServidor() {

        try {
            serverSocket = new ServerSocket(Integer.parseInt(puerto));
            configurarServidor();
        } catch (IOException ex) {
            System.out.println("Error al crear socket servidor " + ex);
        }
    }

    public void configurarServidor() {
        String entrada = null;
        do {
            try {
                socket = serverSocket.accept();
            } catch (IOException ex) {
                System.out.println("Error al crear socket " + ex);
            }
            BufferedReader lector = null;
            try {
                lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ex) {
                System.out.println("Error al crear lector " + ex);
            }
            PrintWriter escritor = null;
            try {
                escritor = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ex) {
                System.out.println("Error al crear escritor " + ex);
            }
            try {
                entrada = lector.readLine();
            } catch (IOException ex) {
                System.out.println("Error al leer mensaje " + ex);
            }
            String cadena;
            String contCompleto = "";
            if (entrada.equalsIgnoreCase("fin")) {
                try {
                    System.out.println("me voy");
                    socket.close();
                    serverSocket.close();
                    System.exit(0);
                } catch (IOException ex) {
                    System.out.println("Error al cerrar sockets " + ex);
                }

            } else {
                File directorio = new File(entrada);
                if (directorio.exists()) {
                    File[] contendio = directorio.listFiles();
                    for (File object : contendio) {
                        if (object.isFile()) {
                            contCompleto = contCompleto + "Nombre archivo: " + object.getName() + System.lineSeparator();
                        } else {
                            if (object.isDirectory()) {
                                contCompleto = contCompleto + "Nombre del directorio: " + object.getName() + System.lineSeparator();
                            }
                        }
                    }
                } else {
                    escritor.println("ruta no existente");
                }

            }
            escritor.println(contCompleto);
        } while (!entrada.equalsIgnoreCase("fin"));
    }

}
