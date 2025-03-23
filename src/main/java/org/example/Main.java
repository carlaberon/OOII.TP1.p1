package org.example;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        var jose = new Participante("234566", "Jose Perez", 1);
        RegistroInscriptos registro = new ArchivoDeInscriptos("F:\\proyectos\\sistemas\\materias2025\\primer cuatrimestre\\orientacion a objetos II\\carpeta\\archivoInscriptos.txt");
//        RegistroInscriptos registro = new RegistroInscriptosDAO();
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 15);
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        Concurso unConcurso = new Concurso(1, "Un Concurso", fechaInicio, fechaFin, registro);
        unConcurso.nuevaInscripcion(jose, fechaInscripcion);
    }
}
