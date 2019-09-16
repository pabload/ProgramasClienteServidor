//version final//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private String ip;
    private String puerto;
    private Socket socket;

    public Cliente(String ip, String puerto) {
        this.ip = ip;
        this.puerto = puerto;
        this.socket = null;
    }

    public void CrearCliente() {
        try {
            socket = new Socket(ip, Integer.parseInt(puerto));
            EscribirCliente();
        } catch (Exception e) {
            System.out.println("Error al crear socket del cliente " + e);
        }
    }

    public void EscribirCliente() {
        PrintWriter escritor;
        try {
            escritor = new PrintWriter(socket.getOutputStream(), true);
            try {
                BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String datos;
                String datosEntrada;
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    datos = scanner.nextLine();
                    try {
                        escritor.println(datos);
                        if (datos.equals("fin")) {
                            cerrarSocket(socket);
                        }
                        datosEntrada = lector.readLine();
                        System.out.println(datosEntrada);
                        //cerrarSocket(socket);
                    } catch (IOException e) {
                        System.out.println("error al mandar mensaje " + e);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al crear lector " + ex);
            }

        } catch (IOException ex) {
            System.out.println("Error al crear escrito " + ex);
        }
    }

    public void cerrarSocket(Socket socket) {
        try {
            socket.close();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error al cerrar sockets " + e);
        }
    }

}
