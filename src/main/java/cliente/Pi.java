package cliente;

import calcular.Tarea;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Implementacion de la tarea que calcula el valor de Pi
 * Usa la formula de Machin: pi/4 = 4*arctan(1/5) - arctan(1/239)
 */
public class Pi implements Tarea<BigDecimal>, Serializable {

    private static final long serialVersionUID = 227L;

    /** Constantes usadas en el calculo de pi */
    private static final BigDecimal CUATRO = BigDecimal.valueOf(4);

    /** Modo de redondeo durante el calculo */
    private static final int modoRedondeo = BigDecimal.ROUND_HALF_EVEN;

    /** Digitos de precision despues del punto decimal */
    private final int digitos;
    
    /**
     * Constructor que define la precision del calculo
     */
    public Pi(int digitos) {
        this.digitos = digitos;
    }

    /**
     * Metodo que ejecuta el calculo de Pi
     */
    public BigDecimal ejecutar() {
        return calcularPi(digitos);
    }

    /**
     * Calcula el valor de pi con el numero especificado de digitos
     * despues del punto decimal usando la formula de Machin
     */
    public static BigDecimal calcularPi(int digitos) {
        int escala = digitos + 5;
        BigDecimal arctan1_5 = arctan(5, escala);
        BigDecimal arctan1_239 = arctan(239, escala);
        BigDecimal pi = arctan1_5.multiply(CUATRO).subtract(
                                  arctan1_239).multiply(CUATRO);
        return pi.setScale(digitos, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Calcula el valor en radianes del arcotangente del inverso
     * del entero proporcionado usando la expansion en series de potencias
     * arctan(x) = x - (x^3)/3 + (x^5)/5 - (x^7)/7 + (x^9)/9 ...
     */   
    public static BigDecimal arctan(int inversoX, int escala) {
        BigDecimal resultado, numerador, termino;
        BigDecimal invX = BigDecimal.valueOf(inversoX);
        BigDecimal invX2 = BigDecimal.valueOf(inversoX * inversoX);

        numerador = BigDecimal.ONE.divide(invX, escala, modoRedondeo);
        resultado = numerador;
        int i = 1;
        do {
            numerador = numerador.divide(invX2, escala, modoRedondeo);
            int denominador = 2 * i + 1;
            termino = numerador.divide(BigDecimal.valueOf(denominador),
                             escala, modoRedondeo);
            if ((i % 2) != 0) {
                resultado = resultado.subtract(termino);
            } else {
                resultado = resultado.add(termino);
            }
            i++;
        } while (termino.compareTo(BigDecimal.ZERO) != 0);
        return resultado;
    }
}
