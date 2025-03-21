package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Mecanico extends Trabajo {

    private static final float FACTOR_HORA;
    private static final float FACTOR_PRECIO_MATERIAL;
    private float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {

    }

    public Mecanico(Mecanico mecanico) {

    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void añadirPrecioMaterial(float precioMaterial) {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    @Override
    public float getPrecioEspecifico() {

    }

    @Override
    public String toString() {
        return String.format("[precioMaterial=%s]", precioMaterial);
    }
}
