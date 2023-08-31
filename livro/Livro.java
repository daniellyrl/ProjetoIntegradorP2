package livro;

import java.util.Objects;

public class Livro {

    private int id;
    private String titulo;
    private int autor;
    private int editora;
    private int genero;
    private int ano;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getEditora() {
        return editora;
    }

    public void setEditora(int editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return """
                ID: %d
                Livro: %s
                Autor: %s
                Ano: %d
                Editora: %s
                """.formatted(id, titulo, autor, ano, editora);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final Livro livro = (Livro) obj;
        return Double.compare(livro.id, id) == 0 && ano == livro.ano && Objects.equals(titulo, livro.titulo)
                && Objects.equals(autor, livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, autor, editora, ano);
    }


    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
}

