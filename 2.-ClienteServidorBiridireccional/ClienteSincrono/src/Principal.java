//version final//
public class Principal {

    public static void main(String[] args) throws Exception {
        try {
            Cliente cliente = new Cliente(args[0], args[1]);
            cliente.CrearCliente();
        } catch (Exception e) {
            System.out.println("Error al iniciar el cliente " + e);
        }

    }

}
