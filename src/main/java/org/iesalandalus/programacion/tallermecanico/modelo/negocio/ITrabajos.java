package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface ITrabajos {
    default List<Trabajo> get() {
        return new ArrayList<>(listaTrabajos);
    }

    default List<Trabajo> get(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Trabajo> trabajosCliente = new ArrayList<>();
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                trabajosCliente.add(trabajo);
            }
        }
        return trabajosCliente;
    }

    default List<Trabajo> get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        List<Trabajo> trabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                trabajosVehiculo.add(trabajo);
            }
        }
        return trabajosVehiculo;
    }

    default void insertar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        comprobaarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        listaTrabajos.add(trabajo);
    }

    default Trabajo añadirHoras(Trabajo trabajo, int horas) {
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajoAbierto != null) {
            trabajoAbierto.añadirHoras(horas);
            return trabajoAbierto;
        }
        throw new TallerMecanicoExcepcion("No hay un trabajo abierto para ese vehículo.");
    }

    default Trabajo añadirPrecioMaterial(Trabajo trabajo, float precioMaterial) {
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajoAbierto != null) {
            trabajoAbierto.añadirPrecioMaterial(precioMaterial);
            return trabajoAbierto;
        }
        throw new TallerMecanicoExcepcion("No hay un trabajo abierto para ese vehículo.");
    }

    default Trabajo cerrar(Trabajo trabajo) {
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajoAbierto != null) {
            trabajoAbierto.cerrar(LocalDate.now());
            return trabajoAbierto;
        }
        throw new TallerMecanicoExcepcion("No hay un trabajo abierto para ese vehículo.");
    }

    default Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        int index = listaTrabajos.indexOf(trabajo);
        return (index != -1) ? listaTrabajos.get(index) : null;
    }

    default void borrar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (!listaTrabajos.remove(trabajo)) {
            throw new TallerMecanicoExcepcion("No se pudo borrar el trabajo porque no existe.");
        }
    }
}
