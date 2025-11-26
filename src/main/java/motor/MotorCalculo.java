package motor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import calcular.Calcular;
import calcular.Tarea;

public class MotorCalculo implements Calcular {

    public MotorCalculo() {
        super();
    }

    public <T> T ejecutarTarea(Tarea<T> t) {
        return t.ejecutar();
    }

    public static void main(String[] args) {
        try {
            String nombre = "Calcular";
            Calcular motor = new MotorCalculo();
            Calcular stub = (Calcular) UnicastRemoteObject.exportObject(motor, 0);
            
            // CREAR EL REGISTRY PROGRAMATICAMENTE
            Registry registro = null;
            try {
                // Intentar conectar a un registry existente
                registro = LocateRegistry.getRegistry(1099);
                registro.list(); // Probar si esta disponible
                System.out.println("Usando RMI Registry existente en puerto 1099");
            } catch (Exception e) {
                // Si no existe, crear uno nuevo
                System.out.println("Creando RMI Registry en puerto 1099...");
                registro = LocateRegistry.createRegistry(1099);
            }
            
            registro.rebind(nombre, stub);
            System.out.println("MotorCalculo enlazado y listo");
            System.out.println("Esperando conexiones de clientes...");
            System.out.println("Presiona Ctrl+C para detener el servidor");
        } catch (Exception e) {
            System.err.println("Excepcion en MotorCalculo:");
            e.printStackTrace();
        }
    }
}