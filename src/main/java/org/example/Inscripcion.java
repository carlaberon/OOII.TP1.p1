package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Inscripcion {
    private Participante participante;
    private Concurso concurso;
    private LocalDateTime fechaInscripcion;

    public Inscripcion(Participante participante, Concurso concurso, LocalDateTime fechaInscripcion) {
        this.participante = participante;
        this.concurso = concurso;
        this.fechaInscripcion = fechaInscripcion;
    }

    public boolean estaInscripto(Participante participante) {
        return this.participante.equals(participante);
    }

    public LocalDate obtenerFechaInscripcion() {
        LocalDate fechaIncripcion = this.fechaInscripcion.toLocalDate();
        return fechaIncripcion;
    }

}