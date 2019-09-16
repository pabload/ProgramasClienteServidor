//version final//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Cliente {

    private String ip;
    private String puerto;
    private String datos;
    private Socket socket;

    public Cliente(String ip, String puerto, String datos) {
        this.ip = ip;
        this.puerto = puerto;
        this.datos = datos;
        this.socket = null;
    }

    public void CrearCliente() {
        try {
            socket = new Socket(ip, Integer.parseInt(puerto));
            ConfigurarCliente();
        } catch (IOException ex) {
            System.out.println("Error al crear socket " + ex);
        }
    }

    public void ConfigurarCliente() {
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.out.println("Error al crear escritor " + ex);
        }
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Error al crear lector " + ex);
        }
        String datosEntrada = "";

        escritor.println(datos);
        while (true) {
            try {
                //while (true) {
                datosEntrada = lector.readLine();
            } catch (IOException ex) {
                System.out.println("Error al leer respuesta " + ex);
            }
            if ((datosEntrada.equals(""))) {
                System.exit(0);
            }
            System.out.println(datosEntrada);
        }
    }

}

