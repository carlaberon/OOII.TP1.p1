package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class RegistroInscriptosDAO implements RegistroInscriptos {

    public RegistroInscriptosDAO() {
    }

    @Override
    public void registrarInscripto(LocalDate fechaInscripcion, int id, int id1) {

        PreparedStatement statement;
        Connection conn;
        try {
            conn = ConnectionManager.getConnection();

            statement = conn
                    .prepareStatement("INSERT INTO inscripciones(fecha_inscripcion, id_participante, id_concurso)" + "VALUES(?, ?, ?)");


            statement.setDate(1, java.sql.Date.valueOf(fechaInscripcion));
            statement.setInt(2, id);
            statement.setInt(3, id1);

            statement.executeUpdate();


        } catch (SQLException e1) {
        } finally {
            try {
                ConnectionManager.disconnect();
            } catch (SQLException e) {

            }
        }
    }

}

