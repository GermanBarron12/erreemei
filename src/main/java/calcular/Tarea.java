package calcular;

/**
 * Interfaz que define una tarea generica
 * Las clases que implementen esta interfaz deben ser Serializables
 */
public interface Tarea<T> {
    T ejecutar();
}