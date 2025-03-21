package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private static int contadorId = 1;
    private final String nombre;
    private int id;
    private List<Participante> inscriptos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaInscripcion;
    private Exportador exportador;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin, Exportador exportador) {
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
        this.id = contadorId++;
        this.exportador = exportador;
    }

    public void export() {
        this.exportador.export(this.toCSV());
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

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append("dd/mm/yyyy, id_participante, id_concurso").append(System.lineSeparator());
        sb.append(formatearFecha(fechaInscripcion)).append(",").append(participante().id()).append(",").append(this.id).append(System.lineSeparator());
        return sb.toString();
    }

    public String formatearFecha(LocalDate fechaInscripcion) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaInscripcion.format(formatter);
        return fechaFormateada;
    }

    public Participante participante() {
        return inscriptos.get(inscriptos.size() - 1);
    }

    public boolean validarFechas(LocalDate fechaInscripcion) {
        return fechaInscripcion.isBefore(this.fechaInicio) || fechaInscripcion.isAfter(this.fechaFin);
    }

    public void gestionarInscripcion(Participante participante, LocalDate fechaInscripcion) {
        if (!participanteInscripto(participante)) {
            this.inscriptos.add(participante);
            this.fechaInscripcion = fechaInscripcion;
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

    public int id() {
        return this.id;
    }


}
