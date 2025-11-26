package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import calcular.Calcular;

/**
 * Programa cliente que calcula Pi usando el motor remoto
 * Argumentos: [direccion_servidor] [digitos_precision]
 */
public class CalcularPi {
    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Uso: java cliente.CalcularPi <host_servidor> <digitos>");
            System.out.println("Ejemplo: java cliente.CalcularPi localhost 10");
            System.exit(1);
        }
        
        try {
            String nombre = "Calcular";
            String hostServidor = args[0];
            int digitos = Integer.parseInt(args[1]);
            
            System.out.println("Conectando al servidor: " + hostServidor);
            Registry registro = LocateRegistry.getRegistry(hostServidor);
            Calcular calc = (Calcular) registro.lookup(nombre);
            
            System.out.println("Calculando Pi con " + digitos + " digitos...");
            Pi tarea = new Pi(digitos);
            BigDecimal pi = calc.ejecutarTarea(tarea);
            
            System.out.println("=========================================");
            System.out.println("Valor de Pi calculado: " + pi);
            System.out.println("=========================================");
        } catch (Exception e) {
            System.err.println("Excepcion en CalcularPi:");
            e.printStackTrace();
        }
    }    
}

