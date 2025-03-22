package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;

public class ArchivoDeInscriptos implements RegistroInscriptos {
    private String path;

    public ArchivoDeInscriptos(String filePath) {
        this.path = filePath;
    }

    @Override
    public void registrarInscripto(LocalDate fechaInscripcion, int id, int id1) {
        String linea = fechaInscripcion.toString() + ", " + id + ", " + id1;
        final Path path = Paths.get(this.path); //crea una instancia
        try {
            Files.write(path, Arrays.asList(linea), StandardCharsets.UTF_8,
                    Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
