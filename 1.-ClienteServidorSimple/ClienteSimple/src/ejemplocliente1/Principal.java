//version final//
package ejemplocliente1;

public class Principal {

    public static void main(String[] args) {
        try {
            Cliente cliente = new Cliente(args[0], args[1], args[2]);
            cliente.crearSocketCliente();
        } catch (Exception e) {
            System.out.println("Error al iniciar cliente " + e);
        }

    }

}
