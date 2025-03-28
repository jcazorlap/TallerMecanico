package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.Map;
import java.util.HashMap;

public enum Evento {
    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3, "Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar clientes"),
    MODIFICAR_CLIENTE(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar vehículo"),
    BUSCAR_VEHICULO(7, "Buscar vehículo"),
    BORRAR_VEHICULO(8, "Borrar vehículo"),
    LISTAR_VEHICULOS(9, "Listar vehículos"),
    INSERTAR_REVISION(10, "Insertar revisión"),
    INSERTAR_MECANICO(20, "Insertar mecanico"),
    BUSCAR_TRABAJO(11, "Buscar trabajo"),
    BORRAR_TRABAJO(12, "Borrar trabajo"),
    LISTAR_TRABAJOS(13, "Listar trabajos"),
    LISTAR_TRABAJOS_CLIENTE(14, "Listar trabajos por cliente"),
    LISTAR_TRABAJOS_VEHICULO(15, "Listar trabajos por vehículo"),
    AÑADIR_HORAS_TRABAJO(16, "Añadir horas a trabajo"),
    AÑADIR_PRECIO_MATERIAL_REVISION(17, "Añadir precio del material a revisión"),
    CERRAR_TRABAJO(18, "Cerrar trabajo"),
    SALIR(19, "Salir");

    private int codigo;
    private String texto;
    private static final Map<Integer, Evento> eventos = new HashMap<>();

    static {
        for (Evento opcion : values()) {
            eventos.put(opcion.codigo, opcion);
        }
    }

    private Evento(int numeroOpcion, String mensaje) {
        this.codigo = numeroOpcion;
        this.texto = mensaje;
    }

    public static boolean esValida(int numeroOpcion) {
        return eventos.containsKey(numeroOpcion);
    }

    public static Evento get(int numeroOpcion) {
        if (!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("Opción no válida.");
        }
        return eventos.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%d. %s", codigo, texto);
    }
}

