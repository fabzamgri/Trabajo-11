package com.azahartech.eventdev.utilidad;

import java.time.LocalDate;

/**
 * Clase de utilidad que contiene métodos estáticos para validar datos
 * comunes en la aplicación EventDEV.
 *
 * No está diseñada para ser instanciada.
 */
public class UtilidadValidacion {

    // Constructor privado para evitar que alguien haga 'new UtilidadValidacion()'
    private UtilidadValidacion() {
        throw new IllegalStateException("Clase de utilidad");
    }

    /**
     * Comprueba si una cadena de texto tiene formato básico de email.
     * @param email El texto a validar.
     * @return true si contiene '@' y '.', y no es nulo.
     */
    public static boolean esEmailValido(String email) {
        if (email == null) {
            return false;
        }
        // Validación simple usando métodos de la clase String
        return email.contains("@") && email.contains(".");
    }

    /**
     * Comprueba si un número de tarjeta tiene la longitud estándar de 16 dígitos.
     * @param numeroTarjeta El número a validar.
     * @return true si la longitud es exactamente 16.
     */
    public static boolean esTarjetaValida(String numeroTarjeta) {
        if (numeroTarjeta == null) {
            return false;
        }
        // Verificamos solo la longitud (en el futuro usaremos Regex para ver si son números)
        return numeroTarjeta.length() == 16;
    }

    /**
     * Comprueba si una puntuación está dentro del rango permitido (1-5).
     * @param puntuacion La puntuación a validar.
     * @return true si está entre 1 y 5 (ambos inclusive).
     */
    public static boolean esPuntuacionValida(int puntuacion) {
        return puntuacion >= 1 && puntuacion <= 5;
    }

    /**
     * Comprueba si una fecha es posterior al día de hoy.
     * @param fecha La fecha a validar.
     * @return true si la fecha es futura.
     */
    public static boolean esFechaFutura(LocalDate fecha) {
        if (fecha == null) {
            return false;
        }
        return fecha.isAfter(LocalDate.now());
    }

    /**
     * Comprueba si un texto es válido (no es nulo y no está vacío).
     * @param texto El texto a validar.
     * @return true si contiene texto útil.
     */
    public static boolean esTextoValido(String texto) {
        // .trim() elimina espacios en blanco al principio y al final
        return texto != null && !texto.trim().isEmpty();
    }
}