package ejerciciosTP1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class RegistroInscriptosDAO implements RegistroInscriptos {
    //data access object
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

            int a = statement.executeUpdate();
            if (a == 0) {
                throw new RuntimeException("No fue posible realizar la carga");
            }


        } catch (SQLException e1) {
            //excepcion
            throw new RuntimeException("SQLException", e1);
        } finally {
            try {
                ConnectionManager.disconnect();
            } catch (SQLException e) {
                //excepcion
                throw new RuntimeException("SQLException", e);
            }
        }
    }

}

