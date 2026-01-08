package com.azahartech.eventdev.modelo;

public class DetallePago {
    private String tipoTarjeta;
    private String numeroTarjeta;

    public DetallePago(String tipoTarjeta, String numeroTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
        this.numeroTarjeta = numeroTarjeta;
    }

    // Método para mostrar la tarjeta enmascarada por seguridad
    public String getNumeroEnmascarado() {
        if (numeroTarjeta != null && numeroTarjeta.length() > 4) {
            return "**** **** **** " + numeroTarjeta.substring(numeroTarjeta.length() - 4);
        }
        return numeroTarjeta;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", tipoTarjeta, getNumeroEnmascarado());
    }
}