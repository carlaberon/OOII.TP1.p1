package ejerciciosTP1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    final String FECHAINSCRIPCION_VACIA = "La fecha de inscripción no puede ser null";
    final String FECHA_INSCRIPCION_INCORRECTA = "Fecha incorrecta...";
    final String FECHA_FUERA_DE_RANGO = "La fecha está fuera del rango de inscripción...";

    private final String nombre;
    private int id;
    private List<Participante> inscriptos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaInscripcion;
    private RegistroInscriptos registro;
    private IMailService servicioMensajeria;


    public Concurso(int id, String nombre, LocalDate fechaInicio, LocalDate fechaFin, RegistroInscriptos registro, IMailService servicioMensajeria) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new RuntimeException(FECHA_INSCRIPCION_INCORRECTA);
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new RuntimeException(FECHA_INSCRIPCION_INCORRECTA);
        }
        if (esFechaNula(fechaInicio) || esFechaNula(fechaFin)) {
            throw new RuntimeException(FECHAINSCRIPCION_VACIA);
        }

        this.nombre = nombre;
        this.inscriptos = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.id = id;
        this.registro = registro;
        this.servicioMensajeria = servicioMensajeria;

    }

    public boolean participanteInscripto(Participante participante) {
        return this.inscriptos.contains(participante);
    }

    public void nuevaInscripcion(Participante participante, LocalDate fechaInscripcion) {
        if (validarFechas(fechaInscripcion)) {
            throw new RuntimeException(FECHA_FUERA_DE_RANGO);
        } else {
            gestionarInscripcion(participante, fechaInscripcion);
        }

    }
//
//    public String toCSV() throws IllegalAccessException {
//        StringBuilder sb = new StringBuilder();
//        sb.append("dd/mm/yyyy, id_participante, id_concurso").append(System.lineSeparator());
//        try {
//            String fechaFormateada = formatearFecha(fechaInscripcion);
//        } catch (IllegalArgumentException e) {
//            throw new IllegalAccessException("La fecha no es válida :" + e.getMessage());
//        }
//
//        sb.append(formatearFecha(fechaInscripcion)).append(",").append(participante().id()).append(",").append(this.id).append(System.lineSeparator());
//        return sb.toString();
//    }


    public Participante participante() {
        return inscriptos.get(inscriptos.size() - 1);
    }

    public boolean validarFechas(LocalDate fechaInscripcion) {
        return fechaInscripcion.isBefore(this.fechaInicio) || fechaInscripcion.isAfter(this.fechaFin);
    }

    public void gestionarInscripcion(Participante participante, LocalDate fechaInscripcion) {
        if (!participanteInscripto(participante)) {
            this.inscriptos.add(participante);
            this.fechaInscripcion = fechaInscripcion;
            gestionarPuntos(participante, fechaInscripcion);
            this.registro.registrarInscripto(fechaInscripcion, participante.id(), this.id);
            this.servicioMensajeria.enviarCorreo(participante.email(), "Inscripción", "Usted a realizado la inscripción...");
        }
    }

    public void gestionarPuntos(Participante participante, LocalDate fechaInscripcion) {
        if (this.fechaInicio.isEqual(fechaInscripcion)) {
            participante.sumarPuntos();
        }
    }

    public int cantidadInscriptos() {
        return this.inscriptos.size();
    }

    public int id() {
        return this.id;
    }

    private boolean esFechaNula(LocalDate fecha) {
        if (fecha == null) {
            return true;
        }
        return false;
    }
}
