package mx.edu.utez.client;

import javafx.scene.transform.Scale;
import mx.edu.utez.server.Handler;
import mx.edu.utez.server.usuario;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JavaClient {

    public static void main(String[] args )
            throws MalformedURLException, XmlRpcException {
        Scanner leer = new Scanner(System.in);

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL( new URL("http://localhost:1300" ) );

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig( config );

        int opc = 0, status, id;
        boolean result;
        String name, lastname, email, password, date_registed;

        do {
            System.out.println("Elegir una opcion");
            System.out.println("1.- Create");
            System.out.println("2.- Read [all]");
            System.out.println("3.- Update");
            System.out.println("4.- Delete");
            System.out.println("5.- Salir");
            opc = leer.nextInt();
            switch (opc){
                case 1:
                    System.out.println("Introducir nombre");
                    name = leer.next();
                    System.out.println("Introducir apellido");
                    lastname = leer.next();
                    System.out.println("Introducir email");
                    email = leer.next();
                    System.out.println("Introducir contraseña");
                    password = leer.next();
                    System.out.println("Introducir estatus (0/1)");
                    status = leer.nextInt();
                    Object[] params = { name, lastname, email, password, status};
                    result = (Boolean) client.execute("Handler.createUser",  params);
                    System.out.println( result );
                    break;
                case 2:
                    System.out.println("Mostrar todo");
                    for (usuario usuario : new Handler().findAll()){
                        System.out.println("Id: "+usuario.getId());
                        System.out.println("Nombre: "+usuario.getName());
                        System.out.println("Apellido: "+usuario.getLastname());
                        System.out.println("Email: "+usuario.getEmail());
                        System.out.println("Contraseña: "+usuario.getPassword());
                        System.out.println("Fecha de Registro: "+usuario.getDate_registered());
                        System.out.println("Estatus: "+usuario.getStatus());
                        System.out.println("--------------------------------------------------------------------------");
                    }
                    break;
                case 3:
                    System.out.println("Actualizar");
                    System.out.println("introducir ID");
                    id= leer.nextInt();
                    System.out.println("Introducir nombre");
                    name = leer.next();
                    System.out.println("Introducir apellido");
                    lastname = leer.next();
                    System.out.println("Introducir email");
                    email = leer.next();
                    System.out.println("Introducir contraseña");
                    password = leer.next();
                    System.out.println("Introducir estatus");
                    status = leer.nextInt();
                    Object[] parametros = {id, name, lastname, email, password, status};
                    boolean result1 = (Boolean) client.execute("Handler.update",  parametros);
                    System.out.println( result1 );
                    break;
                case 4:
                    System.out.println("Introducir id");
                    id= leer.nextInt();
                    Object[] usuario = { id };
                    boolean eliminado = (Boolean) client.execute("Handler.delete",  usuario);
                    System.out.println( eliminado );
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Error..");
            };
        }while (opc !=5);
        //Object[] params = { 1, 3 };
        //int result = (Integer) client.execute("Handler.suma",  params);

    }
}
