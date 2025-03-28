package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorEventos {
    private Map<Evento, List<ReceptorEventos>> receptores;

    public GestorEventos() {
        this.receptores = new HashMap<>();
    }

    public void suscribir(ReceptorEventos receptor, Evento evento) {
        receptores.computeIfAbsent(evento, k -> new ArrayList<>());
        receptores.get(evento).add(receptor);
    }

    public void desuscribir(ReceptorEventos receptor, Evento evento) {

        List<ReceptorEventos> receptoresDelEvento = receptores.get(evento);
        if (receptoresDelEvento != null) {
            receptoresDelEvento.remove(receptor);
        }
    }

    public void notificar(Evento evento) {
        List<ReceptorEventos> receptoresDelEvento = receptores.get(evento);

        if (receptoresDelEvento != null) {
            for (ReceptorEventos receptor : receptoresDelEvento) {
                receptor.actualizar(evento);
            }
        }
    }
}

