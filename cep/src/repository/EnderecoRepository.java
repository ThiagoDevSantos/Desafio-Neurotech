package repository;

import model.Endereco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnderecoRepository {
    public static Connection conectar() {
        Connection connection;
        String driverClass = "com.mysql.cj.jdbc.Driver";
        String databaseUrl = "jdbc:mysql://localhost:3306/cep";
        String username = "root";
        String password = "1234";
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(databaseUrl, username, password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void criarTabelaEndereco() {
        try (Connection connection = conectar()) {
            String sql = "CREATE TABLE IF NOT EXISTS endereco (" +
                    "cep VARCHAR(8) NOT NULL," +
                    "uf VARCHAR(2) NOT NULL," +
                    "cidade VARCHAR(255) NOT NULL," +
                    "vizinhanca VARCHAR(255) NOT NULL," +
                    "rua VARCHAR(255) NOT NULL" +
                    ")";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela 'endereco': " + e.getMessage());
        }
    }

    public void salvarEndereco(Endereco endereco) {
        criarTabelaEndereco();
        try (Connection connection = conectar()) {
            String sql = "INSERT INTO endereco (cep, uf, cidade, vizinhanca, rua) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, endereco.getCep());
            statement.setString(2, endereco.getUf());
            statement.setString(3, endereco.getCidade());
            statement.setString(4, endereco.getVizinhanca());
            statement.setString(5, endereco.getRua());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar endereco no banco de dados: " + e.getMessage());
        }
    }
}
