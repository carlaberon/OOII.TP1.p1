package org.example;

import java.util.Objects;

public class Participante {

    private String dni;
    private String nombre;
    private int puntos;

    public Participante(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public void sumarPuntos() {
        this.puntos += puntos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(nombre, that.nombre);
    }        /*the implementation of  the method contains use equals*/

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}