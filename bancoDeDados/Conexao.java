package bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection geraConexao() {
        String url = "jdbc:mysql://localhost:3306/AchadosPerdidos";
        String user = "root";
        String password = "root";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão realizada com sucesso.");
            return connection;
        } catch (SQLException ex) {
            System.err.println("Ocorreu um erro ao tentar se conectar com o banco: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
