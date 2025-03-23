package org.example;

import java.util.Objects;

public class Participante {

    private int id;
    private String dni;
    private String nombre;
    private int puntos;
    private String email;

    public Participante(String dni, String nombre, int id, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.id = id;
        this.email = email;
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

    public int id() {
        return this.id;
    }

    public String email() {
        return this.email;
    }
}