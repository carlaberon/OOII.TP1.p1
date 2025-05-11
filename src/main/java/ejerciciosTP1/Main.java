package ejerciciosTP1;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        var carlaPerez = new Participante("234566", "Carla Perez", 2, "archcarlab@gmail.com");
        var servicioEmail = new ServiceMail("eff9a8ce985569", "e0d3a5cbd9a31c", "true", "true", "sandbox.smtp.mailtrap.io", "587");
        
//        RegistroInscriptos registro = new ArchivoDeInscriptos("F:\\proyectos\\sistemas\\materias2025\\primer cuatrimestre\\orientacion a objetos II\\carpeta\\archivoInscriptos.txt");
        RegistroInscriptos registro = new RegistroInscriptosDAO();
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 15);
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        Concurso unConcurso = new Concurso(1, "Un Concurso", fechaInicio, fechaFin, registro, servicioEmail);
        unConcurso.nuevaInscripcion(carlaPerez, fechaInscripcion);

    }
}
