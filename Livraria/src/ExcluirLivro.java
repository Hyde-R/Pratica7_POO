import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ExcluirLivro {
    private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
    private final String user = "postgres";
    private final String password = "123456";

    private static final String QUERY_EXCLUIR = "delete from livro where id_isbn = ?";

    public void excluirLivro(String id_isbn) throws SQLException {
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);
             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_EXCLUIR)) {
            preparedStatement.setString(1, id_isbn);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}