package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision extends Trabajo {

    private static final float FACTOR_HORA = 35f;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    public Revision(Revision revision) {
        super(revision);
    }

    @Override
    public float getPrecioEspecifico() {
        return (estaCerrada()) ? FACTOR_HORA * getHoras() : 0;
    }

    @Override
    public String toString() {
        String cadena;
        if(!estaCerrada()) {
            cadena = String.format("Revision -> %s - %s (%s - ): %d horas", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getHoras());
        } else {
            cadena = String.format("Revision -> %s - %s (%s - %s): %d horas, %.2f total", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getHoras());
        }
        return cadena;
    }
}