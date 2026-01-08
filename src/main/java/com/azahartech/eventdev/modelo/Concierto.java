package com.azahartech.eventdev.modelo;

import java.time.LocalDate;

/**
 * Clase que representa un tipo específico de Evento: un Concierto.
 * Hereda todos los atributos y métodos de la clase Evento.
 */
public class Concierto extends Evento {

    // Atributo específico de esta clase hija
    private String bandaPrincipal;

    // Constructor
    public Concierto(String nombre, LocalDate fecha, double precio, Recinto recinto, String bandaPrincipal) {
        // 1. Llamada obligatoria al constructor del padre (Evento)
        super(nombre, fecha, precio, recinto);

        // 2. Inicialización del atributo propio
        this.bandaPrincipal = bandaPrincipal;
    }

    // Método de acceso propio
    public String obtenerBandaPrincipal() {
        return bandaPrincipal;
    }

    // Sobrescritura (Override) para especializar el comportamiento
    @Override
    public String toString() {
        // Reutilizamos el toString() del padre con super.toString() y añadimos lo nuestro
        return super.toString() + String.format(" | Banda: %s", bandaPrincipal);
    }

    // Si usas el método mostrarInformacion() en lugar de toString():
    /*
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion(); // Muestra los datos básicos
        System.out.println("   -> Banda Principal: " + this.bandaPrincipal);
    }
    */
}