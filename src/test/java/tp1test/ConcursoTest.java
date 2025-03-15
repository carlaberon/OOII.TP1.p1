package tp1test;
import org.example.Concurso;
import org.example.Inscripcion;
import org.example.Participante;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {
    @Test
    public void test01() {
        //un participante se inscribe en un concurso
        //setup
        var jose = new Participante("234566", "Jose Perez");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin);
        unConcurso.nuevaInscripcion(new Inscripcion(jose, unConcurso, LocalDateTime.now()));
        //Inscripcion.inscribirAEn(jose, unConcurso);
        assertTrue(unConcurso.participanteInscripto(jose));
        //probar contains
        //la implementacion del método contains usa equals

        assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void test02() {
        //un participante se inscribe en un concurso el primer dia de abierta la inscripción
        //setup
        var maria = new Participante("234567", "Maria Perez");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDateTime fechaInscripcion= fechaInicio.atStartOfDay();
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin);
        //exercise
        unConcurso.nuevaInscripcion(new Inscripcion(maria, unConcurso, fechaInscripcion));
        //Inscripcion.inscribirAEn(jose, unConcurso);

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
        LocalDate fechaInscripcion1 = LocalDate.of(2025, 2,31);
        LocalDateTime fechaInscripcion2= fechaInscripcion1.atStartOfDay();
        Concurso unConcurso = new Concurso("Un Concurso", fechaInicio, fechaFin);
        //exercise
        unConcurso.nuevaInscripcion(new Inscripcion(juana, unConcurso, fechaInscripcion2));
        //Inscripcion.inscribirAEn(jose, unConcurso);

        //verify
        //assertFalse(unConcurso.participanteInscripto(juana));
        assertEquals(1, unConcurso.cantidadInscriptos());
        //asertThrows(), si lanza la excepción,
            //no se agregó a la colección
    }

}
