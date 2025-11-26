package calcular;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz remota que define el motor de calculo
 * Permite ejecutar tareas de forma remota
 */
public interface Calcular extends Remote {
    <T> T ejecutarTarea(Tarea<T> t) throws RemoteException;
}