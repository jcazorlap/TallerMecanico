package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Mecanico;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {

    private List<Trabajo> listaTrabajos;

    public Trabajos() {
        listaTrabajos = new ArrayList<>();
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(listaTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        List<Trabajo> trabajosCliente = new ArrayList<>();
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                trabajosCliente.add(trabajo);
            }
        }
        return trabajosCliente;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        List<Trabajo> trabajosVehiculo = new ArrayList<>();
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                trabajosVehiculo.add(trabajo);
            }
        }
        return trabajosVehiculo;
    }

    @Override
    public void insertar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        listaTrabajos.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getCliente().equals(cliente) && trabajo.getVehiculo().equals(vehiculo) && trabajo.getFechaInicio().equals(fechaInicio)) {
                throw new TallerMecanicoExcepcion("Ya existe un trabajo con esos datos.");
            }
        }
    }

    @Override
    public Trabajo añadirHoras(Trabajo trabajo, int horas) {
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajoAbierto != null) {
            trabajoAbierto.añadirHoras(horas);
            return trabajoAbierto;
        }
        throw new TallerMecanicoExcepcion("No hay un trabajo abierto para ese vehículo.");
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No puedo operar sobre un vehículo nulo");
        Trabajo trabajoEncontrado = null;
        Iterator<Trabajo> iteradorTrabajos = coleccionTrabajos.iterator();
        while (iteradorTrabajos.hasNext() && trabajoEncontrado == null) {
            Trabajo trabajo = iteradorTrabajos.next();
            if (trabajo.getVehiculo().equals(vehiculo) && !trabajo.estaCerrada()) {
                trabajoEncontrado = trabajo;
            }

        }
        if (trabajoEncontrado == null) {
            throw new TallerMecanicoExcepcion("No existe ningun trabajo abierto para dicho vehiculo.");
        }
        return trabajoEncontrado;

    }

    @Override
    public Trabajo añadirPrecioMaterial(Trabajo trabajo, float precioMaterial) {
        Trabajo trabajoEncontrado = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajoEncontrado instanceof Mecanico mecanico) {
            mecanico.añadirPrecioMaterial(precioMaterial);

        } else {
            throw new TallerMecanicoExcepcion("No se puede añadir precio para este tipo de trabajos.");
        }
        return trabajoEncontrado;
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo) {
        Trabajo trabajoAbierto = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajoAbierto != null) {
            trabajoAbierto.cerrar(LocalDate.now());
            return trabajoAbierto;
        }
        throw new TallerMecanicoExcepcion("No hay un trabajo abierto para ese vehículo.");
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        int index = listaTrabajos.indexOf(trabajo);
        return (index != -1) ? listaTrabajos.get(index) : null;
    }

    @Override
    public void borrar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (!listaTrabajos.remove(trabajo)) {
            throw new TallerMecanicoExcepcion("No se pudo borrar el trabajo porque no existe.");
        }
    }
}

