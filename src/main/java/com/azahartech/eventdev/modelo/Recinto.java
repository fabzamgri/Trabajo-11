package com.azahartech.eventdev.modelo;

public class Recinto {
    private String nombre;
    private String direccion;
    private int aforoMaximo;

    public Recinto(String nombre, String direccion, int aforoMaximo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.aforoMaximo = aforoMaximo;
    }

    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public int getAforoMaximo() { return aforoMaximo; }

    @Override
    public String toString() {
        return String.format("Recinto: %s (%s) - Aforo: %d", nombre, direccion, aforoMaximo);
    }
}
