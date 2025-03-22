package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
    @Test
    public void test01() throws IllegalAccessException {
        //un participante se inscribe en un concurso
        //setup
        var jose = new Participante("234566", "Jose Perez", 1);
        var maria = new Participante("234567", "Maria Perez", 2);
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 15);
        LocalDate fechaInscripcion2 = LocalDate.of(2025, 3, 16);
//        var registro = new ArchivoDeInscriptos("F:\\proyectos\\sistemas\\materias2025\\primer cuatrimestre\\orientacion a objetos II\\carpeta\\registroInscriptos.txt");
        String pathFake = "";
        var registro = new RegistroInscriptosFake(pathFake);
        Concurso unConcurso = new Concurso(1, "Un Concurso", fechaInicio, fechaFin, registro);
        //exercise
        unConcurso.nuevaInscripcion(jose, fechaInscripcion);
        unConcurso.nuevaInscripcion(maria, fechaInscripcion2);
        String esperado = "15/03/2025, 1, 1\n" + "16/03/2025, 2, 1\n";
        //verify
        //++ comprobar que registroinscriptoFake esté bien programado ++//
        assertEquals(esperado.replace("\n", System.lineSeparator()), registro.data());
        assertTrue(unConcurso.participanteInscripto(jose));
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(2, unConcurso.cantidadInscriptos());
    }


    @Test
    public void test02() throws IllegalAccessException {
        //un participante se inscribe en un concurso el primer dia de abierta la inscripción
        //setup
        var maria = new Participante("234567", "Maria Perez", 1);
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        String pathFake = "";
        var registro = new RegistroInscriptosFake(pathFake);
        Concurso unConcurso = new Concurso(1, "Un Concurso", fechaInicio, fechaFin, registro);
        //exercise
        unConcurso.nuevaInscripcion(maria, fechaInicio);
        String esperado = "01/03/2025, 1, 1\n";
        //verify
        assertEquals(esperado.replace("\n", System.lineSeparator()), registro.data());
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(1, unConcurso.cantidadInscriptos());

    }

    @Test
    public void test03() {
//        //un participante intenta inscribirse fuera del rango de inscripción
//        //setup
        var juana = new Participante("234567", "Juana Perez", 1);
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 5, 31);
        String pathFake = "";
        var registro = new RegistroInscriptosFake(pathFake);
        Concurso unConcurso = new Concurso(1, "Un Concurso", fechaInicio, fechaFin, registro);
//        //opcion 1
////        //exercise & verify
////        RuntimeException exception = assertThrows(RuntimeException.class, () -> unConcurso.nuevaInscripcion(juana, fechaInscripcion)); //puedo usar assert en exercise?
////        unConcurso.export();
////        String esperado = "dd/mm/yyyy, id_participante, id_concurso\n" +
////                "05/03/2025,1,1\n";
////        assertEquals(esperado.replace("\n", System.lineSeparator()), exportador.data());
////        assertEquals("La fecha está fuera del rango de inscripción...", exception.getMessage());
////        assertEquals(0, unConcurso.cantidadInscriptos());
////        assertFalse(unConcurso.participanteInscripto(juana));
//
        //opcion 2 separar exercise de verify
        RuntimeException exception = null;
        try {
            unConcurso.nuevaInscripcion(juana, fechaInscripcion);
        } catch (RuntimeException exception1) {
            exception = exception1;
        }
        //Verify
//        assertEquals(null, registro.data()); //No se llega a ejecutar el gestionarInscripcion
        assertNotNull(exception);
        assertEquals("La fecha está fuera del rango de inscripción...", exception.getMessage());
        assertEquals(0, unConcurso.cantidadInscriptos());
        assertFalse(unConcurso.participanteInscripto(juana));
    }

}
