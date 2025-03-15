package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private final String nombre;
    private List<Inscripcion> inscriptos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.inscriptos = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean participanteInscripto(Participante participante) {
        boolean esta = false;
        int i = 0;
        List<Inscripcion> l = this.inscriptos;
        while (!esta && i < l.size()) {
            if (l.get(i).estaInscripto(participante)) {
                esta = true;
            } else {
                i++;
            }
        }
        return esta;
        //return this.inscriptos.contains(participante);
    }


    public void nuevaInscripcion(Inscripcion inscripcion) {
        if (inscripcion.isBefore(inscripcion) || inscripcion.isAfter(inscripcion)){
            throw new RuntimeException("La fecha está fuera del rango de inscripción...");
        }else {
            this.inscriptos.add(inscripcion);}
    }


    public int cantidadInscriptos() {
        return this.inscriptos.size();
    }

    public LocalDate obtenerFechaInicio(){
        LocalDate fechaInicio = this.fechaInicio;
        return fechaInicio;
    }

    public LocalDate obtenerFechaFin(){
        LocalDate fechaFin = this.fechaFin;
        return fechaFin;
    }


}
