package livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepositorio {

    private final Connection connection;
    private Object stmt;

    public LivroRepositorio(Connection connection) {
        this.connection = connection;
    }

    public int salvar(Livro livro) {
        String sql = "" +
                "INSERT INTO livro(titulo, ano, IDAutor, IDEditora, IDGenero) VALUES (?, ?, ?, ?, ?)";

        try (final var stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAno());
            stmt.setInt(3, livro.getAutor());
            stmt.setInt(4, livro.getEditora());
            stmt.setInt(5, livro.getGenero());

            stmt.executeUpdate();

            ResultSet tableKeys = stmt.getGeneratedKeys();
            tableKeys.next();
            int idLivro = tableKeys.getInt(1);
            stmt.close();
            return idLivro;
        } catch (Exception ex) {
            System.err.println("Falha ao tentar salvar o livro: " + ex.getMessage());
            ex.printStackTrace();
            return 0;
        }
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM livro WHERE titulo = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Livro> listaDeLivrosEncontrada = new ArrayList<>();
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getInt("IDAutor"));
                livro.setEditora(rs.getInt("IDEditora"));
                livro.setGenero(rs.getInt("IDGenero"));
                livro.setAno(rs.getInt("ano"));
                livro.setId(rs.getInt("id"));
                listaDeLivrosEncontrada.add(livro);
            }

            rs.close();
            stmt.close();
            return listaDeLivrosEncontrada;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
