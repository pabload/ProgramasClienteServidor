//version final//
package ejemplocliente1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private String ip;
    private String puerto;
    private String mensaje;
    private Socket socket;

    public Cliente(String ip, String puerto, String Mensaje) {
        this.ip = ip;
        this.puerto = puerto;
        this.mensaje = Mensaje;
        this.socket = null;
    }

    public void crearSocketCliente() {
        try {
            socket = new Socket(ip, Integer.parseInt(puerto));
            EscribirMensaje();
        } catch (IOException e) {
            System.out.println("Error al crear el socket " + e.toString());
            System.exit(1);
        }
    }

    public void EscribirMensaje() {
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter(socket.getOutputStream(), true);
            escritor.println(mensaje);
            cerrarSocket();
        } catch (IOException e) {
            System.out.println("Error al escribir mensaje " + e.toString());
            System.exit(2);
        }
    }

    public void cerrarSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar socket " + e.toString());
            System.exit(3);
        }
    }

}
