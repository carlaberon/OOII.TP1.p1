package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private final String nombre;
    private List<Participante> inscriptos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaInscripcion;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new RuntimeException("Fecha incorrecta...");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new RuntimeException("Fecha incorrecta...");
        }
        this.nombre = nombre;
        this.inscriptos = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean participanteInscripto(Participante participante) {
        return this.inscriptos.contains(participante);
    }

    public void nuevaInscripcion(Participante participante, LocalDate fechaInscripcion) {
        if (validarFechas(fechaInscripcion)) {
            throw new RuntimeException("La fecha está fuera del rango de inscripción...");
        } else {
            gestionarInscripcion(participante, fechaInscripcion);
        }
    }

    public boolean validarFechas(LocalDate fechaInscripcion) {
        return fechaInscripcion.isBefore(this.fechaInicio) || fechaInscripcion.isAfter(this.fechaFin);
    }

    public void gestionarInscripcion(Participante participante, LocalDate fechaInscripcion) {
        if (!participanteInscripto(participante)) {
            this.inscriptos.add(participante);
            gestionarPuntos(participante, fechaInscripcion);
        }
    }

    public void gestionarPuntos(Participante participante, LocalDate fechaInscripcion) {
        if (this.fechaFin.isEqual(fechaInscripcion)) {
            participante.sumarPuntos();
        }
    }

    public int cantidadInscriptos() {
        return this.inscriptos.size();
    }


}
