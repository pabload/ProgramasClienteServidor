//verison final//

public class PrincipalCliente {

    public static void main(String[] args){
        try {
            Cliente cliente =new Cliente(args[0],args[1],args[2]);
            cliente.CrearCliente();
        } catch (Exception e) {
            System.out.println("Error al iniciar el cliente "+e);
        }

    }
  

}
