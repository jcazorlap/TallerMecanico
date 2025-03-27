package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.List;

public interface Modelo {
    void comenzar();

    void terminar();

    void insertar(Cliente cliente);

    void insertar(Vehiculo vehiculo);

    void insertar(Revision revision);

    Cliente buscar(Cliente cliente);

    Vehiculo buscar(Vehiculo vehiculo);

    Revision buscar(Revision revision);

    Cliente modificar(Cliente cliente, String nombre, String telefono);

    Revision añadirHoras(Revision revision, int horas);

    Revision añadirPrecioMaterial(Revision revision, float precioMaterial);

    Revision cerrar(Revision revision, LocalDate fechaFin);

    void borrar(Cliente cliente);

    void borrar(Vehiculo vehiculo);

    void borrar(Revision revision);

    List<Cliente> getClientes();

    List<Vehiculo> getVehiculos();

    List<Trabajo> getTrabajos();

    List<Revision> getTrabajos(Cliente cliente);

    List<Revision> getTrabajos(Vehiculo vehiculo);
}
