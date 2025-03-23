package org.example;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionManagerTest {
    @Test
    public void testConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(connection, "La conexión debería ser válida y no nula.");
    }
}
