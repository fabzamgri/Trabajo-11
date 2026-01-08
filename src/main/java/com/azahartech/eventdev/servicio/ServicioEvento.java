package com.azahartech.eventdev.servicio;

import com.azahartech.eventdev.modelo.*;
import java.time.LocalDate;

public class ServicioEvento {

    // Simulamos una base de datos con un único evento y un usuario logueado
    private Evento eventoActual;
    private Usuario usuarioActual;

    /**
     * El constructor inicializa el sistema con datos de prueba.
     * Crea todo el grafo de objetos necesario (Recinto -> Evento, DetallePago -> Usuario).
     */
    public ServicioEvento() {
        // 1. Preparamos el entorno del Evento
        Recinto recinto = new Recinto("Estadio Olímpico", "Av. del Deporte, s/n", 5000);
        // Creamos un evento para dentro de 2 meses
        this.eventoActual = new Evento("Festival de Verano", LocalDate.now().plusMonths(2), 65.0, recinto);

        // 2. Preparamos el entorno del Usuario
        this.usuarioActual = new Usuario("AlexDev", "alex@azahar.tech", 300.00);
        DetallePago tarjeta = new DetallePago("VISA", "4567890123456789");
        this.usuarioActual.setDetallePago(tarjeta);
    }

    /**
     * Método principal de negocio.
     * Orquesta la compra verificando aforo y saldo.
     *
     * @param cantidad Número de entradas a comprar.
     * @return Un objeto Tique si la compra es exitosa, o null si falla.
     */
    public Tique realizarCompra(int cantidad) {
        System.out.println("--- INICIANDO PROCESO DE COMPRA ---");

        // 1. Verificar Disponibilidad (Lógica de Negocio del Evento)
        if (eventoActual.obtenerPlazasDisponibles() < cantidad) {
            System.out.println("Error: No hay suficientes entradas disponibles.");
            return null;
        }

        // 2. Calcular Coste
        double costeTotal = eventoActual.getPrecio() * cantidad;
        System.out.printf("Solicitando %d entradas. Coste total: %.2f EUR%n", cantidad, costeTotal);

        // 3. Intentar Cobrar (Lógica de Negocio del Usuario)
        // El método realizarPago devuelve true si se pudo cobrar, false si no hay saldo
        boolean pagoRealizado = usuarioActual.realizarPago(costeTotal);

        if (pagoRealizado) {
            // 4. Si el pago fue bien, confirmamos la venta en el evento
            eventoActual.registrarVenta(cantidad);

            // 5. Generamos el Tique (El producto final de la transacción)
            Tique nuevoTique = new Tique(eventoActual, usuarioActual, cantidad);

            System.out.println("¡Compra completada con éxito!");
            return nuevoTique;

        } else {
            System.out.println("Error: Saldo insuficiente en la cuenta del usuario.");
            return null;
        }
    }

    // Métodos auxiliares para que la App pueda mostrar qué hay disponible
    public void mostrarInformacionContexto() {
        System.out.println("\n--- ESTADO ACTUAL DEL SISTEMA ---");
        System.out.println(eventoActual.toString()); // Llama al toString del Evento
        System.out.println(usuarioActual.toString()); // Llama al toString del Usuario
        System.out.println("---------------------------------\n");
    }
}