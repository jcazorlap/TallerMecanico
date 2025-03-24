package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trabajos implements ITrabajos {

    private List<Trabajo> listaTrabajos;

    public Trabajos() {
        listaTrabajos = new ArrayList<>();
    }

    private void comprobaarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getCliente().equals(cliente) && trabajo.getVehiculo().equals(vehiculo) && trabajo.getFechaInicio().equals(fechaInicio)) {
                throw new TallerMecanicoExcepcion("Ya existe un trabajo con esos datos.");
            }
        }
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) {
        for (Trabajo trabajo : listaTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo) && !trabajo.estaCerrada()) {
                return trabajo;
            }
        }
        return null;
    }

}

