package usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaRepositorio {

    private final Connection connection2;
    private Objects stmt;

    public PessoaRepositorio(Connection connection2) {

        this.connection2 = connection2;
    }

    public void salvar(Pessoa pessoa) {

        String sql = "" +
                "INSERT INTO usuario (cpf, nome, rua, bairro, cep, cidade, telefone1, telefone2)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection2.prepareStatement(sql);

            stmt.setString(1, pessoa.getCpf());
            stmt.setString(2, pessoa.getNome());
            stmt.setString(3, pessoa.getRua());
            stmt.setString(4, pessoa.getBairro());
            stmt.setString(5, pessoa.getCep());
            stmt.setString(6, pessoa.getCidade());
            stmt.setString(7, pessoa.getTelefone1());
            stmt.setString(8, pessoa.getTelefone2());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Pessoa> buscarPorNome(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome = ?";
        try {
            PreparedStatement stmt = connection2.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Pessoa> listaDePessoasEncontradas = new ArrayList<>();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();

                pessoa.setNome(rs.getString("nome"));

                listaDePessoasEncontradas.add(pessoa);
            }
            rs.close();
            stmt.close();
            return listaDePessoasEncontradas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
