import java.sql.*;

public class PesquisaLivro {

    private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
    private final String user = "postgres";
    private final String password = "123456";
    private static final String QUERY_VALOR = "select * from livro where vl_preco > ?";
    private static final String QUERY_TITULO = "select * from livro where nm_titulo like ?";

    public void pesquisaLivroPreco(double vl_preco){
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_VALOR)) {
            preparedStatement.setDouble(1, vl_preco);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_isbn");
                String nome_titulo = rs.getString("nm_titulo");
                double valor = rs.getDouble("vl_preco");
                System.out.println(id + " - " + nome_titulo + " - " + valor);
            }
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void pesquisaLivroTitulo(String nm_titulo){
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);
             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_TITULO)) {
            nm_titulo += "%";
            preparedStatement.setString(1, nm_titulo);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_isbn");
                String nome_titulo = rs.getString("nm_titulo");
                System.out.println(id + " - " + nome_titulo);
            }
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
