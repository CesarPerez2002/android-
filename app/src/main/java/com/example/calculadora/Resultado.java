package com.example.calculadora;

import java.io.Serializable;

/**
 * Clase que representa el resultado de una operación matemática.
 * Recibe los valores y el resultado mediante su constructor.
 */
public class Resultado implements Serializable {
    private final double numero1;
    private final double numero2;
    private final String operacion;
    private final double resultado;

    // Constructor que recibe el resultado y los detalles de la operación
    public Resultado(double numero1, double numero2, String operacion, double resultado) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.operacion = operacion;
        this.resultado = resultado;
    }

    public double getNumero1() {
        return numero1;
    }

    public double getNumero2() {
        return numero2;
    }

    public String getOperacion() {
        return operacion;
    }

    public double getResultado() {
        return resultado;
    }
}
