package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision extends Trabajo {

    private static final float FACTOR_HORA;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        super.horas = 0;
        this.precioMaterial = 0;
    }

    public Revision(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("No es posible copiar una revisión nula.");
        }
        this.cliente = new Cliente(revision.cliente);
        this.vehiculo = revision.vehiculo;
        this.fechaInicio = revision.fechaInicio;
        this.fechaFin = revision.fechaFin;
        this.horas = revision.horas;
        this.precioMaterial = revision.precioMaterial;
    }

    public float getPrecioEspecifico() {
        return precioEspecifico;
    }

    @Override
    public String toString() {
        return String.format("Revisión de %s con %s - Inicio: %s, Fin: %s, Horas: %d, Material: %.2f",
                cliente, vehiculo,
                fechaInicio.format(FORMATO_FECHA),
                (fechaFin != null ? fechaFin.format(FORMATO_FECHA) : "No cerrada"),
                horas, precioMaterial);
    }
}