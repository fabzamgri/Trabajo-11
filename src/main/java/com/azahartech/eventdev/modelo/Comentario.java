package com.azahartech.eventdev.modelo;

import java.util.UUID;

public class Comentario {
    private String id;
    private Evento evento;
    private Usuario autor;
    private int puntuacion; // 1 a 5
    private String texto;

    public Comentario(Evento evento, Usuario autor, int puntuacion, String texto) {
        this.id = UUID.randomUUID().toString();
        this.evento = evento;
        this.autor = autor;

        // Validación básica de rango
        if (puntuacion < 1) this.puntuacion = 1;
        else if (puntuacion > 5) this.puntuacion = 5;
        else this.puntuacion = puntuacion;

        this.texto = texto;
    }

    @Override
    public String toString() {
        return String.format("Valoración de %s sobre %s: [%d/5] %s",
                autor.getNombreUsuario(), evento.getNombre(), puntuacion, texto);
    }
}