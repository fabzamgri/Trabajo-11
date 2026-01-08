package com.azahartech.eventdev.presentacion;

import com.azahartech.eventdev.modelo.*;
import com.azahartech.eventdev.servicio.ServicioEvento;
import com.azahartech.eventdev.utilidad.UtilidadValidacion;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Clase principal de la aplicación EventDEV.
 * Demostración del sprint de la Unidad 4: Desarrollo de Clases y Herencia.
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("   EVENTDEV - SISTEMA DE GESTIÓN (v1.0)  ");
        System.out.println("=========================================\n");

        // ---------------------------------------------------------
        // 1. USO DE MÉTODOS ESTÁTICOS (Clase de Utilidad)
        // ---------------------------------------------------------
        System.out.println(">>> 1. VALIDACIÓN DE DATOS (MÉTODOS ESTÁTICOS)");

        System.out.print("Introduzca un email para registrar usuario: ");
        String emailInput = scanner.nextLine();

        // Llamada estática: No hacemos 'new UtilidadValidacion()'
        if (!UtilidadValidacion.esEmailValido(emailInput)) {
            System.out.println("❌ Error: El formato del email no es válido.");
            // En un caso real podríamos pedirlo de nuevo, aquí usamos uno por defecto
            emailInput = "usuario_defecto@azahar.tech";
            System.out.println("   -> Usando email por defecto: " + emailInput);
        } else {
            System.out.println("✅ Email validado correctamente.");
        }

        // ---------------------------------------------------------
        // 2. CREACIÓN DE OBJETOS BASE Y COMPOSICIÓN
        // ---------------------------------------------------------
        System.out.println("\n>>> 2. CREANDO EL ENTORNO (COMPOSICIÓN)");

        // Crear Recinto
        Recinto recintoPrincipal = new Recinto("Wizink Center", "Av. Felipe II, Madrid", 15000);
        System.out.println("   -> Recinto creado: " + recintoPrincipal.getNombre());

        // Crear Detalles de Pago
        DetallePago miTarjeta = new DetallePago("VISA", "1234567890123456");

        // Crear Usuario y añadir composición
        Usuario usuarioActivo = new Usuario("AlbaDev", emailInput, 150.0);
        usuarioActivo.setDetallePago(miTarjeta); // Composición: El usuario TIENE detalles de pago

        // Mostramos el usuario (su toString llamará al toString de DetallePago)
        System.out.println("   -> Usuario registrado:");
        System.out.println(usuarioActivo.toString());

        // ---------------------------------------------------------
        // 3. HERENCIA Y POLIMORFISMO
        // ---------------------------------------------------------
        System.out.println("\n>>> 3. GESTIÓN DE EVENTOS (HERENCIA)");

        System.out.println("¿Qué tipo de evento deseas crear?");
        System.out.println("1. Concierto");
        System.out.println("2. Partido");
        System.out.print("Selecciona (1/2): ");
        int tipoEvento = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Variable polimórfica: 'nuevoEvento' puede guardar un Concierto o un Partido
        Evento nuevoEvento = null;

        if (tipoEvento == 1) {
            // Creando un Concierto (Hija)
            System.out.print("Nombre de la banda: ");
            String banda = scanner.nextLine();

            nuevoEvento = new Concierto(
                    "Gran Festival Rock",
                    LocalDate.now().plusMonths(1),
                    45.0,
                    recintoPrincipal,
                    banda
            );
        } else {
            // Creando un Partido (Hija)
            System.out.print("Equipo Local: ");
            String local = scanner.nextLine();
            System.out.print("Equipo Visitante: ");
            String visitante = scanner.nextLine();

            nuevoEvento = new Partido(
                    "Final de Copa",
                    LocalDate.now().plusWeeks(2),
                    80.0,
                    recintoPrincipal,
                    local,
                    visitante,
                    "Copa del Rey"
            );
        }

        // POLIMORFISMO EN ACCIÓN:
        // Llamamos a .mostrar(), pero se ejecutará la versión de Concierto o de Partido
        // según lo que haya elegido el usuario.
        System.out.println("\n--- FICHA DEL EVENTO CREADO ---");
        System.out.println(nuevoEvento.toString());

        // ---------------------------------------------------------
        // 4. LÓGICA DE NEGOCIO Y SERVICIO
        // ---------------------------------------------------------
        System.out.println("\n>>> 4. SIMULACIÓN DE COMPRA (CAPA DE SERVICIO)");

        // Instanciamos el servicio (normalmente usaría su propia 'base de datos',
        // aquí simulamos una compra directa sobre los objetos creados para ver la interacción).

        System.out.print("Cantidad de entradas a comprar: ");
        int cantidad = scanner.nextInt();

        // Verificamos aforo usando método del Evento
        if (nuevoEvento.obtenerPlazasDisponibles() >= cantidad) {
            double costeTotal = nuevoEvento.getPrecio() * cantidad;

            // Verificamos saldo y cobramos usando método del Usuario
            if (usuarioActivo.realizarPago(costeTotal)) {
                // Registramos venta en el Evento
                nuevoEvento.registrarVenta(cantidad);

                // Creamos el Tique (Clase de Relación)
                Tique tiqueGenerado = new Tique(nuevoEvento, usuarioActivo, cantidad);

                System.out.println("\n✅ ¡Compra exitosa! Aquí tiene su entrada:");
                System.out.println(tiqueGenerado.toString()); // El toString del Tique muestra datos de Usuario y Evento
            } else {
                System.out.println("❌ Error: Saldo insuficiente.");
            }
        } else {
            System.out.println("❌ Error: No hay suficientes entradas.");
        }

        System.out.println("\n=========================================");
        System.out.println("   FIN DE LA DEMOSTRACIÓN   ");
        System.out.println("=========================================");

        scanner.close();
    }
}