package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
//    @Test
//    public void test01() {
//        //un participante se inscribe en un concurso
//        //setup
//        var jose = new Participante("234566", "Jose Perez");
//        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
//        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
//        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 1);
//        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin);
//        //exercise
//        unConcurso.nuevaInscripcion(jose, fechaInscripcion);
//        //verify
//        assertTrue(unConcurso.participanteInscripto(jose));
//        assertEquals(1, unConcurso.cantidadInscriptos());
//    }


    @Test
    public void test02() {
        //un participante se inscribe en un concurso el primer dia de abierta la inscripción
        //setup
        var maria = new Participante("234567", "Maria Perez");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        var exportador = new EnMemoriaExportador();
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin, exportador);
        //exercise
        unConcurso.nuevaInscripcion(maria, fechaInicio);
        unConcurso.export();
        String esperado = "dd/mm/yyyy, id_participante, id_concurso\n" +
                "01/03/2025,1,1\n";
        //verify
        assertEquals(esperado.replace("\n", System.lineSeparator()), exportador.data());
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(1, unConcurso.cantidadInscriptos());

    }

    @Test
    public void test03() {
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
        } catch (RuntimeException e) {
            exception = e;
        }

/*      hay que verificar otras cosas, la inscripción no se realiza por lo tanto no hay fecha de inscripcion
        si no hay fecha de inscripción, ¿exportador.data qué hace? 
        unConcurso.export();
        String esperado = "dd/mm/yyyy, id_participante, id_concurso\n" +
                "05/03/2025,1,1\n";
        assertEquals(esperado.replace("\n", System.lineSeparator()), exportador.data());*/

        //Verify
        assertNotNull(exception);
        assertEquals("La fecha está fuera del rango de inscripción...", exception.getMessage());
        assertEquals(0, unConcurso.cantidadInscriptos());
        assertFalse(unConcurso.participanteInscripto(juana));

    }

}
