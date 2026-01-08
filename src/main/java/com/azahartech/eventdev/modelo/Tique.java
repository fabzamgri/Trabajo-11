package com.azahartech.eventdev.modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Tique {
    private String id;
    private Evento evento;
    private Usuario comprador;
    private LocalDate fechaCompra;
    private int cantidadEntradas;
    private double precioTotal;

    public Tique(Evento evento, Usuario comprador, int cantidadEntradas) {
        this.id = UUID.randomUUID().toString();
        this.evento = evento;
        this.comprador = comprador;
        this.cantidadEntradas = cantidadEntradas;
        this.precioTotal = evento.getPrecio() * cantidadEntradas;
        this.fechaCompra = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format("""
                -------------------------------
                TIQUE DE COMPRA - EventDEV
                ID: %s
                Fecha: %s
                -------------------------------
                EVENTO: %s
                LUGAR: %s
                -------------------------------
                COMPRADOR: %s
                CANTIDAD: %d
                TOTAL PAGADO: %.2f EUR
                -------------------------------
                """,
                id, fechaCompra,
                evento.getNombre(), evento.getRecinto().getNombre(),
                comprador.getNombreUsuario(), cantidadEntradas, precioTotal);
    }
}