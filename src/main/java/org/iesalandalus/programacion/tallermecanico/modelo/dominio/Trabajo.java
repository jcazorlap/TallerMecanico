package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Trabajo {
    protected static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float FACTOR_DIA = 1.5f;

    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
        this.horas = 0;
    }

    protected Trabajo(Trabajo trabajo) {
        if (trabajo == null) {
            throw new NullPointerException("El trabajo no puede ser nulo.");
        }
        this.cliente = new Cliente(trabajo.cliente);
        this.vehiculo = new Vehiculo(trabajo.vehiculo);
        this.fechaInicio = trabajo.fechaInicio;
        this.fechaFin = trabajo.fechaFin;
        this.horas = trabajo.horas;
    }

    public Trabajo copiar() {
        if (this instanceof Revision) {
            return new Revision((Revision) this);
        }

        throw new IllegalArgumentException("No se puede copiar un trabajo de este tipo.");
    }

    public Trabajo get(Vehiculo vehiculo) {
        Cliente clienteGenerico = new Cliente("Cliente Generico", "00000000A", "000000000");
        LocalDate fechaInicioGenerica = LocalDate.now();
        if (this instanceof Revision) {
            return new Revision(clienteGenerico, vehiculo, fechaInicioGenerica);
        }

        throw new IllegalArgumentException("Tipo de trabajo no soportado.");
    }

    public Cliente getCliente() {
        return new Cliente(cliente);
    }

    private void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("El cliente no puede ser nulo.");
        }
        this.cliente = new Cliente(cliente);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    private void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("El vehículo no puede ser nulo.");
        }
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio == null) {
            throw new NullPointerException("La fecha de inicio no puede ser nula.");
        }
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a hoy.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        if (fechaFin == null) {
            throw new NullPointerException("La fecha de fin no puede ser nula.");
        }
        if (fechaFin.isBefore(fechaInicio) || fechaFin.isEqual(fechaInicio) || fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio y no puede ser posterior a hoy.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void añadirHoras(int horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas añadidas deben ser mayores que cero.");
        }
        this.horas += horas;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) {
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        if (!estaCerrada()) {
            throw new IllegalStateException("No se puede calcular el precio de un trabajo no cerrado.");
        }
        return (horas * Revision.PRECIO_HORA) + (getDias() * Revision.PRECIO_DIA) + (precioMaterial * Revision.PRECIO_MATERIAL);
    }

    private long getDias() {
        return fechaInicio.until(fechaFin).getDays();
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trabajo trabajo = (Trabajo) obj;
        return Objects.equals(cliente, trabajo.cliente) && Objects.equals(vehiculo, trabajo.vehiculo) && Objects.equals(fechaInicio, trabajo.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio);
    }
}
