import java.sql.*;
import java.util.Scanner;

public class ConexaoPostgre {
    private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
    private final String user = "postgres";
    private final String password = "123456";
    Connection conn = null;
    Scanner sc = new Scanner(System.in);

    private static final String QUERY_TITULO = "select * from livro where nm_titulo = ?";
    private static final String QUERY_VALOR = "select * from livro where vl_preco > ?";

    public Connection connect() {

        try {
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
            //vers�o do postgreeSQL
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
        //conn.close();
    }

    public void getBookByName() {
        // Step 1: Establishing a Connection
        try {
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_TITULO);
            // Step 3: Execute the query or update query
            System.out.println("Digite o nome de um livro: ");
            String titulo = sc.nextLine();
            preparedStatement.setString(1, titulo);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String id = rs.getString("id_isbn");
                String name_titulo = rs.getString("nm_titulo");
                System.out.println(id + " - " + name_titulo);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void getBookByPrices(double vl_preco) {
        // Step 1: Establishing a Connection
        try {
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_VALOR);
            // Step 3: Execute the query or update query
            preparedStatement.setDouble(1, vl_preco);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String id = rs.getString("id_isbn");
                String name_titulo = rs.getString("nm_titulo");
                double valor_livro = rs.getDouble("vl_preco");
                System.out.println(id + " - " + name_titulo + " - " + valor_livro);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ConexaoPostgre app = new ConexaoPostgre();
        app.connect();
        System.out.println("\nRealizando o select na tabela livro pelo titulo");
        app.getBookByName();
        System.out.println("Testanto: ");
        app.getBookByPrices(50);

    }
}