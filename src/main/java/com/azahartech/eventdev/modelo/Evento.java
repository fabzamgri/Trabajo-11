package com.azahartech.eventdev.modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Evento {
    private String id;
    private String nombre;
    private LocalDate fecha;
    private double precio;
    private int entradasVendidas;
    private Recinto recinto; // Relación de composición

    public Evento(String nombre, LocalDate fecha, double precio, Recinto recinto) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
        this.recinto = recinto;
        this.entradasVendidas = 0;
    }

    // Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public LocalDate getFecha() { return fecha; }
    public Recinto getRecinto() { return recinto; }
    public int getEntradasVendidas() { return entradasVendidas; }

    // Lógica de negocio: Calcular plazas libres
    public int obtenerPlazasDisponibles() {
        return recinto.getAforoMaximo() - entradasVendidas;
    }

    // Lógica de negocio: Registrar venta
    public void registrarVenta(int cantidad) {
        if (cantidad <= obtenerPlazasDisponibles()) {
            this.entradasVendidas += cantidad;
        } else {
            // En unidades futuras aquí lanzaremos una excepción
            System.err.println("Error: No hay suficientes entradas disponibles.");
        }
    }

    @Override
    public String toString() {
        return String.format("Evento: %s | Fecha: %s | Precio: %.2f€ | %s | Disponibles: %d",
                nombre, fecha, precio, recinto.toString(), obtenerPlazasDisponibles());
    }
}