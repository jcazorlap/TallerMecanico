package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;

public interface ITrabajos {
    List<Trabajo> get();

    List<Trabajo> get(Cliente cliente);

    List<Trabajo> get(Vehiculo vehiculo);

    void insertar(Trabajo trabajo);

    Trabajo añadirHoras(Trabajo trabajo, int horas);

    Trabajo añadirPrecioMaterial(Trabajo trabajo, float precioMaterial);

    Trabajo cerrar(Trabajo trabajo);

    Trabajo buscar(Trabajo trabajo);

    void borrar(Trabajo trabajo);
}
