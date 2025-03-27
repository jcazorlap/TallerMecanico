package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeloCascada implements org.iesalandalus.programacion.tallermecanico.modelo.Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    @Override
    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
        System.out.println("El modelo ha comenzado.");
    }

    @Override
    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    @Override
    public void insertar(Cliente cliente) {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Revision revision) {
        Cliente clienteEncontrado = clientes.buscar(revision.getCliente());
        Vehiculo vehiculoEncontrado = vehiculos.buscar(revision.getVehiculo());
        if (clienteEncontrado == null || vehiculoEncontrado == null) {
            throw new TallerMecanicoExcepcion("Cliente o vehículo no encontrado para la revisión.");
        }
        revisiones.insertar(new Revision(clienteEncontrado, vehiculoEncontrado, revision.getFechaInicio()));
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Cliente encontrado = clientes.buscar(cliente);
        return (encontrado != null) ? new Cliente(encontrado) : null;
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Revision buscar(Revision revision) {
        Revision encontrada = revisiones.buscar(revision);
        return (encontrada != null) ? new Revision(encontrada) : null;
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public Revision añadirHoras(Revision revision, int horas) {
        return revisiones.añadirHoras(revision, horas);
    }

    @Override
    public Revision añadirPrecioMaterial(Revision revision, float precioMaterial) {
        return revisiones.añadirPrecioMaterial(revision, precioMaterial);
    }

    @Override
    public Revision cerrar(Revision revision, LocalDate fechaFin) {
        return revisiones.cerrar(revision, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) {
        List<Revision> revisionesCliente = this.getTrabajos(cliente);
        for (Revision revision : revisionesCliente) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) {
        List<Revision> revisionesVehiculo = getTrabajos(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Revision revision) {
        revisiones.borrar(revision);
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> copiaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            copiaClientes.add(new Cliente(cliente));
        }
        return copiaClientes;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> copiaTrabajos = new ArrayList<>();
        for (Trabajo trabajo : copiaTrabajos.get(vehiculo)) {
            copiaTrabajos.add(new Trabajo(trabajo) {
                @Override
                public float getPrecioEspecifico() {
                    return 0;
                }
            });
        }
        return copiaTrabajos;
    }

    @Override
    public List<Revision> getTrabajos(Cliente cliente) {
        List<Trabajo> copiaTrabajos = new ArrayList<>();
        for (Trabajo trabajo : copiaTrabajos.get(cliente)) {
            copiaTrabajos.add(new Trabajo(trabajo) {
                @Override
                public float getPrecioEspecifico() {
                    return 0;
                }
            });
        }
        return copiaTrabajos;
    }

    @Override
    public List<Revision> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> copiaTrabajos = new ArrayList<>();
        for (Trabajo trabajo : copiaTrabajos.get(vehiculo)) {
            copiaTrabajos.add(new Trabajo(trabajo) {
                @Override
                public float getPrecioEspecifico() {
                    return 0;
                }
            });
        }
        return copiaTrabajos;
    }
}