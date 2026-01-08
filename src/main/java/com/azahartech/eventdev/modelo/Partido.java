package com.azahartech.eventdev.modelo;

import java.time.LocalDate;

/**
 * Clase que representa un evento deportivo.
 * Hereda de Evento.
 */
public class Partido extends Evento {

    private String equipoLocal;
    private String equipoVisitante;
    private String competicion; // Ej: "La Liga", "Champions"

    public Partido(String nombre, LocalDate fecha, double precio, Recinto recinto, String local, String visitante, String competicion) {
        // Llamada al constructor de la superclase
        super(nombre, fecha, precio, recinto);

        this.equipoLocal = local;
        this.equipoVisitante = visitante;
        this.competicion = competicion;
    }

    public String obtenerEquipoLocal() { return equipoLocal; }
    public String obtenerEquipoVisitante() { return equipoVisitante; }

    @Override
    public String toString() {
        // Muestra la información base + el enfrentamiento específico
        return super.toString() +
                String.format(" | Encuentro: %s vs %s (%s)", equipoLocal, equipoVisitante, competicion);
    }
}