package ejerciciosTP1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    @Test
    public void test01() throws IllegalAccessException {
        //un participante se inscribe en un concurso
        //setup
        var jose = new Participante("234566", "Jose Perez", 1, "jperez@gmail.com");
        var maria = new Participante("234567", "Maria Perez", 2, "mperez@gmail.com");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 15);
        LocalDate fechaInscripcion2 = LocalDate.of(2025, 3, 16);
//        var registro = new ArchivoDeInscriptos("F:\\proyectos\\sistemas\\materias2025\\primer cuatrimestre\\orientacion a objetos II\\carpeta\\registroInscriptos.txt");
        String pathFake = "";
        var registroFake = new RegistroInscriptosFake(pathFake);
        var servicioMensajeriaFake = new ServiceMailFake();
        Concurso unConcurso = new Concurso(1, "Un Concurso", fechaInicio, fechaFin, registroFake, servicioMensajeriaFake);
        //exercise
        unConcurso.nuevaInscripcion(jose, fechaInscripcion);
        unConcurso.nuevaInscripcion(maria, fechaInscripcion2);
        String esperado = "15/03/2025, 1, 1\n" + "16/03/2025, 2, 1\n";

        //verify
        assertTrue(registroFake.startWith("15/03/2025"));
        assertEquals(esperado.replace("\n", System.lineSeparator()), registroFake.data());
        assertTrue(unConcurso.participanteInscripto(jose));
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(2, unConcurso.cantidadInscriptos());
        assertEquals("mperez@gmail.com - Inscripción: Usted a realizado la inscripción...", servicioMensajeriaFake.mail());
    }


    @Test
    public void test02() throws IllegalAccessException {
        //un participante se inscribe en un concurso el primer dia de abierta la inscripción
        //setup
        var maria = new Participante("234567", "Maria Perez", 1, "mperez@gmail.com");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        String pathFake = "";
        var registroInscriptosFake = new RegistroInscriptosFake(pathFake);
        var servicioMensajeria = new ServiceMailFake();
        Concurso unConcurso = new Concurso(1, "Un Concurso", fechaInicio, fechaFin, registroInscriptosFake, servicioMensajeria);
        //exercise
        unConcurso.nuevaInscripcion(maria, fechaInicio);
        String esperado = "01/03/2025, 1, 1\n";
        //verify
        assertEquals(esperado.replace("\n", System.lineSeparator()), registroInscriptosFake.data());
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(1, unConcurso.cantidadInscriptos());
        assertEquals("mperez@gmail.com - Inscripción: Usted a realizado la inscripción...", servicioMensajeria.mail());

    }

    @Test
    public void test03() {
        //un participante intenta inscribirse fuera del rango de inscripción
        //setup
        var juana = new Participante("234567", "Juana Perez", 1, "juanaPerez@gmail.com");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 5, 31);
        String pathFake = "";
        var registro = new RegistroInscriptosFake(pathFake);
        var servicioMensajeria = new ServiceMailFake();
        Concurso unConcurso = new Concurso(1, "Un Concurso", fechaInicio, fechaFin, registro, servicioMensajeria);
        //opcion 2 separar exercise de verify
        RuntimeException exception = null;
        try {
            unConcurso.nuevaInscripcion(juana, fechaInscripcion);
        } catch (RuntimeException exception1) {
            exception = exception1;
        }
        //Verify
        //verificar que al inscribir a un participante se invoque al m enviarCorreo
        assertEquals(0, unConcurso.cantidadInscriptos());
        assertEquals(null, registro.data()); //No se llega a ejecutar el gestionarInscripcion - no se ejecutan los servicios
        assertNotNull(exception);
        assertEquals("La fecha está fuera del rango de inscripción...", exception.getMessage());
        assertFalse(unConcurso.participanteInscripto(juana));
        assertTrue(servicioMensajeria.noSeEnvioElCorreo());

    }

}
