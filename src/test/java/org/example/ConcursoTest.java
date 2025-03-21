package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
    @Test
    public void test01() throws IllegalAccessException {
        //un participante se inscribe en un concurso
        //setup
        var jose = new Participante("234566", "Jose Perez");
        var exportador = new EnMemoriaExportador();
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 15);
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin, exportador);
        //exercise
        unConcurso.nuevaInscripcion(jose, fechaInscripcion);
        unConcurso.export();
        String esperado = "dd/mm/yyyy, id_participante, id_concurso\n" +
                "15/03/2025,1,1\n";
        //verify
        assertEquals(esperado.replace("\n", System.lineSeparator()), exportador.data());
        //verify
        assertTrue(unConcurso.participanteInscripto(jose));
        assertEquals(1, unConcurso.cantidadInscriptos());
    }


    @Test
    public void test02() throws IllegalAccessException {
        //un participante se inscribe en un concurso el primer dia de abierta la inscripción
        //setup
        var maria = new Participante("234567", "Maria Perez");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        var exportador = new EnMemoriaExportador();
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin, exportador);
        //exercise
        unConcurso.nuevaInscripcion(maria, fechaInicio);
        try {
            unConcurso.export();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        String esperado = "dd/mm/yyyy, id_participante, id_concurso\n" +
                "01/03/2025,2,2\n";
        //verify
        assertEquals(esperado.replace("\n", System.lineSeparator()), exportador.data());
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(1, unConcurso.cantidadInscriptos());

    }

    @Test
    public void test03() throws IllegalAccessException {
        //un participante intenta inscribirse fuera del rango de inscripción
        //setup
        var exportador = new EnMemoriaExportador();
        var juana = new Participante("234567", "Juana Perez");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 5, 31);
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin, exportador);
        //opcion 1
//        //exercise & verify
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> unConcurso.nuevaInscripcion(juana, fechaInscripcion)); //puedo usar assert en exercise?
//        unConcurso.export();
//        String esperado = "dd/mm/yyyy, id_participante, id_concurso\n" +
//                "05/03/2025,1,1\n";
//        assertEquals(esperado.replace("\n", System.lineSeparator()), exportador.data());
//        assertEquals("La fecha está fuera del rango de inscripción...", exception.getMessage());
//        assertEquals(0, unConcurso.cantidadInscriptos());
//        assertFalse(unConcurso.participanteInscripto(juana));

        //opcion 2 separar exercise de verify
        RuntimeException exception = null;
        try {
            unConcurso.nuevaInscripcion(juana, fechaInscripcion);
        } catch (RuntimeException exception1) {
            exception = exception1;
        }
        IllegalAccessException fechaInvalida = null;
        try {
            unConcurso.export();
        } catch (IllegalAccessException e) {
            fechaInvalida = e;
        }

        //Verify
        assertEquals(null, exportador.data());
        assertNotNull(exception);
        assertNotNull(fechaInvalida);
        assertEquals("La fecha está fuera del rango de inscripción...", exception.getMessage());
        assertEquals("La fecha no es válida :La fecha de inscripción no puede ser null", fechaInvalida.getMessage());
        assertEquals(0, unConcurso.cantidadInscriptos());
        assertFalse(unConcurso.participanteInscripto(juana));

    }

}
