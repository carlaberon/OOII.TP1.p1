package ejerciciosTP1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ArchivoDeInscriptos implements RegistroInscriptos {
    final String FECHAINSCRIPCION_VACIA = "La fecha de inscripción no puede ser null";
    private String path;

    public ArchivoDeInscriptos(String filePath) {
        this.path = filePath;
    }

    @Override
    public void registrarInscripto(LocalDate fechaInscripcion, int id, int id1) {
        String linea = formatearFecha(fechaInscripcion) + ", " + id + ", " + id1;
        final Path path = Paths.get(this.path); //crea una instancia
        try {
            Files.write(path, Arrays.asList(linea), StandardCharsets.UTF_8,
                    Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String formatearFecha(LocalDate fechaInscripcion) {
        if (fechaInscripcion == null) {
            throw new IllegalArgumentException(FECHAINSCRIPCION_VACIA);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaInscripcion.format(formatter);
        return fechaFormateada;
    }
}
