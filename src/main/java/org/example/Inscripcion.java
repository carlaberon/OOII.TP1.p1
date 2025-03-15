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

    public boolean isBefore(Inscripcion nuevaIncripcion){
        LocalDate fechaIncripcion = nuevaIncripcion.obtenerFechaInscripcion();
        var before = fechaIncripcion.isBefore(this.concurso.obtenerFechaInicio());
        //verifica que la primer fecha sea anterior a la segunda fecha
        return before;
    }
    public boolean isAfter(Inscripcion nuevaIncripcion){
        LocalDate fechaInscripcion = nuevaIncripcion.obtenerFechaInscripcion();
        var before = fechaInscripcion.isAfter(this.concurso.obtenerFechaFin());
        //verifica que la primer fecha sea posterior a la segunda fecha
        return before;
    }

    public LocalDate obtenerFechaInscripcion(){
        LocalDate fechaIncripcion = this.fechaInscripcion.toLocalDate();
        return fechaIncripcion;
    }

}