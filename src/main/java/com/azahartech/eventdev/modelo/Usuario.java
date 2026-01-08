package com.azahartech.eventdev.modelo;

import java.util.UUID;

public class Usuario {
    private String id;
    private String nombreUsuario;
    private String email;
    private double saldo;
    private DetallePago detallePago; // Relación de composición

    public Usuario(String nombreUsuario, String email, double saldoInicial) {
        this.id = UUID.randomUUID().toString();
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.saldo = saldoInicial;
        this.detallePago = null;
    }

    // Métodos de acceso
    public String getNombreUsuario() { return nombreUsuario; }
    public String getEmail() { return email; }
    public double getSaldo() { return saldo; }

    public void setDetallePago(DetallePago detallePago) {
        this.detallePago = detallePago;
    }

    // Lógica de negocio: Descontar saldo
    // Devuelve true si se pudo pagar, false si no hay saldo
    public boolean realizarPago(double importe) {
        if (this.saldo >= importe) {
            this.saldo -= importe;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String infoPago = (detallePago != null) ? detallePago.toString() : "Sin método de pago";
        return String.format("Usuario: %s | Email: %s | Saldo: %.2f€ | Pago: %s",
                nombreUsuario, email, saldo, infoPago);
    }
}