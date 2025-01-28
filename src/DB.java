import java.sql.*;

public class DB {
    private static final String url = "jdbc:mysql://localhost/atm";
    private static final String username = "root";
    private static final String password = "";

    private Connection connection;

    public DB() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }


    }
}
