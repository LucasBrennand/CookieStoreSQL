package src;

import java.sql.*;
import java.math.BigDecimal;

public class CookieStore {

    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/cookiestore";
            String user = "root";
            String password = "password";
            Connection connection = DriverManager.getConnection(url, user, password);
            listarProdutos(connection);
            inserirNovoProduto(connection);
            atualizarEstoque(connection);
            excluirProduto(connection);
            listarVendasPorFuncionario(connection);
            buscarClientesComCashback(connection);
            connection.close();

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Lista todos os produtos do banco de dados
    public static void listarProdutos(Connection connection) throws SQLException {
        System.out.println("\nListando todos os produtos:");
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM products";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int productID = resultSet.getInt("product_ID");
            String productName = resultSet.getString("product_Name");
            String description = resultSet.getString("description");
            BigDecimal price = resultSet.getBigDecimal("price");
            int stock = resultSet.getInt("stock");

            System.out.println("Produto ID: " + productID);
            System.out.println("Nome do produto: " + productName);
            System.out.println("Descrição: " + description);
            System.out.println("Preço: " + price);
            System.out.println("Estoque: " + stock);
            System.out.println("-------------------");
        }
        resultSet.close();
        statement.close();
    }

    // Insere um novo produto no banco de dados
    public static void inserirNovoProduto(Connection connection) throws SQLException {
        System.out.println("\nInserindo um novo produto:");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO products (product_Name, description, price, stock) VALUES (?, ?, ?, ?)");
        statement.setString(1, "Morango");
        statement.setString(2, "Morango com cobertura de chocolate branco");
        statement.setBigDecimal(3, new BigDecimal("6"));
        statement.setInt(4, 100);

        int rowsAffected = statement.executeUpdate();
        System.out.println("Linhas afetadas: " + rowsAffected);
        statement.close();
    }

    // Atualiza o estoque de um produto no banco de dados
    public static void atualizarEstoque(Connection connection) throws SQLException {
        System.out.println("\nAtualizando o estoque do produto 'Chocolate':");
        PreparedStatement statement = connection.prepareStatement("UPDATE products SET stock = ? WHERE product_Name = ?");
        statement.setInt(1, 50);
        statement.setString(2, "Chocolate");

        int rowsAffected = statement.executeUpdate();
        System.out.println("Linhas afetadas: " + rowsAffected);
        statement.close();
    }

    // Exclui um produto do banco de dados
    public static void excluirProduto(Connection connection) throws SQLException {
        System.out.println("\nExcluindo o produto 'Vanilla':");
        PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE product_Name = ?");
        statement.setString(1, "Vanilla");

        int rowsAffected = statement.executeUpdate();
        System.out.println("Linhas afetadas: " + rowsAffected);
        statement.close();
    }

    // Lista as vendas por cada funcionário
    public static void listarVendasPorFuncionario(Connection connection) throws SQLException {
        System.out.println("\nListando as vendas por cada funcionário:");
        Statement statement = connection.createStatement();
        String query = "SELECT e.name AS employee_name, SUM(p.price) AS total_sold " +
                "FROM employees e " +
                "JOIN sale s ON e.employee_ID = s.employee_id " +
                "JOIN products p ON s.sale_id = p.product_ID " +
                "GROUP BY e.employee_ID, e.name";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String employeeName = resultSet.getString("employee_name");
            BigDecimal totalSold = resultSet.getBigDecimal("total_sold");

            System.out.println("Funcionário: " + employeeName);
            System.out.println("Total vendido: " + totalSold);
            System.out.println("-------------------");
        }
        resultSet.close();
        statement.close();
    }

    // Busca os clientes que possuem cashback
    public static void buscarClientesComCashback(Connection connection) throws SQLException {
        System.out.println("\nBuscando clientes com cashback:");
        Statement statement = connection.createStatement();
        String query = "SELECT c.name AS client_name, sc.cashback AS cashback_total " +
                "FROM clients c " +
                "JOIN special_clients sc ON c.client_ID = sc.client_id";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String clientName = resultSet.getString("client_name");
            double cashbackTotal = resultSet.getDouble("cashback_total");

            System.out.println("Cliente: " + clientName);
            System.out.println("Cashback total: " + cashbackTotal);
            System.out.println("-------------------");
        }
        resultSet.close();
        statement.close();
    }
}