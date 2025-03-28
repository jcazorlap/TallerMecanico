package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.List;

import static org.iesalandalus.programacion.tallermecanico.vista.texto.Consola.*;

public class VistaTexto implements org.iesalandalus.programacion.tallermecanico.vista.Vista {
    @Override
    public GestorEventos gestorEventos() {

    }

    @Override
    public void comenzar() {

    }

    @Override
    public void terminar() {

    }

    @Override
    public Cliente leerCliente() {

    }

    @Override
    public Cliente leerClienteDni() {

    }

    @Override
    public String leerNuevoNombre() {

    }

    @Override
    public String leerNuevoTelefono() {

    }

    @Override
    public Vehiculo leerVehiculo() {

    }

    @Override
    public Vehiculo leerVehiculoMatricula() {

    }

    @Override
    public Trabajo leerRevision() {

    }

    @Override
    public Trabajo leerMecanico() {

    }

    @Override
    public Trabajo leerTrabajoVehiculo() {

    }

    @Override
    public int leerHoras() {

    }

    @Override
    public float leerPrecioMaterial() {

    }

    @Override
    public LocalDate leerFechaCierre() {

    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {

    }

    @Override
    public void mostrarCliente(Cliente cliente) {

    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {

    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {

    }

}