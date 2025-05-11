package ejerciciosTP1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroInscriptosFake implements RegistroInscriptos {
    private String data;

    public RegistroInscriptosFake(String data) {
        this.data = data;
    }

    @Override
    public void registrarInscripto(LocalDate fechaInscripcion, int id, int id1) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatearFecha(fechaInscripcion)).append(", ").append(id).append(", ").append(id1).append(System.lineSeparator());
        this.data += sb.toString();
    }

    private String formatearFecha(LocalDate fechaInscripcion) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaInscripcion.format(formatter);
        return fechaFormateada;
    }

    public boolean startWith(String start) {
        return this.data.startsWith(start);
    }

    public String data() {
        return this.data;
    }
}
