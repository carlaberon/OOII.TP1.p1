package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
    @Test
    public void test01() {
        //un participante se inscribe en un concurso
        //setup
        var jose = new Participante("234566", "Jose Perez");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 1);
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin);
        //exercise
        unConcurso.nuevaInscripcion(jose, fechaInscripcion);
        //verify
        assertTrue(unConcurso.participanteInscripto(jose));
        assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void test02() {
        //un participante se inscribe en un concurso el primer dia de abierta la inscripción
        //setup
        var maria = new Participante("234567", "Maria Perez");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin);
        //exercise
        unConcurso.nuevaInscripcion(maria, fechaInicio);
        //verify
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void test03() {
        //un participante intenta inscribirse fuera del rango de inscripción
        //setup
        var juana = new Participante("234567", "Juana Perez");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 5, 31);
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin);
//        //opcion 1
//        //exercise & verify
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> unConcurso.nuevaInscripcion(juana, fechaInscripcion)); //puedo usar assert en exercise?
//        //verify
//        assertEquals("La fecha está fuera del rango de inscripción...", exception.getMessage());
//        assertEquals(0, unConcurso.cantidadInscriptos());
//        assertFalse(unConcurso.participanteInscripto(juana));

        // opcion 2 separar exercise de verify
        RuntimeException exception = null;
        try {
            unConcurso.nuevaInscripcion(juana, fechaInscripcion);
        } catch (RuntimeException e) {
            exception = e;
        }
        //Verify
        assertNotNull(exception);
        assertEquals("La fecha está fuera del rango de inscripción...", exception.getMessage());
        assertEquals(0, unConcurso.cantidadInscriptos());
        assertFalse(unConcurso.participanteInscripto(juana));

    }

}
