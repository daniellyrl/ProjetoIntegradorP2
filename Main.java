import bancoDeDados.Conexao;
import livro.Livro;
import usuario.Pessoa;
import usuario.PessoaRepositorio;
import livro.LivroRepositorio;

import java.nio.channels.Channels;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner entrada = new Scanner(System.in);

    private static final Connection connection = new Conexao().geraConexao();
    private static final Connection connection2 = new Conexao().geraConexao();
    private static final LivroRepositorio livroRepositorio = new LivroRepositorio(connection);
    private static final PessoaRepositorio pessoaRepositorio = new PessoaRepositorio(connection2);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("""
                    1 - cadastrar livros
                    2 - buscar algum livro
                    3 - cadastrar usuário
                    4 - buscar algum usuário cadastrado
                    9 - sair
                        """);
            opcao = getOpcao();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    buscarLivro();
                    break;
                case 3:
                    cadastrarUsuario();
                    break;
                case 4:
                    buscarUsuario();
                    break;
                case 9:
                    System.out.println("Até a próxima!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }

    private static int getOpcao() {
        try {
            String line = entrada.nextLine();
            int opcao = Integer.parseInt(line);
            System.out.println("Opção escolhida: " + opcao + "\n\n");
            return opcao;
        } catch (Exception ex) {
            return 0;
        }
    }

    private static void cadastrarLivro() {

        Livro livroCriado = new Livro();

        System.out.println("Título: ");
        livroCriado.setTitulo(entrada.nextLine());

        System.out.println("Autor: ");
        livroCriado.setAutor(entrada.nextInt());

        System.out.println("Editora: ");
        livroCriado.setEditora(entrada.nextInt());

        System.out.println("Gênero do livro: ");
        livroCriado.setGenero(entrada.nextInt());

        System.out.println("Ano de publicação: ");
        livroCriado.setAno(entrada.nextInt());

        int idLivro = livroRepositorio.salvar(livroCriado);

        System.out.println("Livro cadastrado!");
        System.out.println("ID: " + idLivro);
    }

    private static void cadastrarUsuario() {

        Pessoa pessoaNova = new Pessoa();

        System.out.println("Nome: ");
        pessoaNova.setNome(entrada.nextLine());

        System.out.println("CPF: ");
        pessoaNova.setCpf(entrada.nextLine());

        System.out.println("Rua: ");
        pessoaNova.setRua(entrada.nextLine());

        System.out.println("Bairro: ");
        pessoaNova.setBairro(entrada.nextLine());

        System.out.println("CEP: ");
        pessoaNova.setCep(entrada.nextLine());

        System.out.println("Cidade: ");
        pessoaNova.setCidade(entrada.nextLine());

        System.out.println("Telefone: ");
        pessoaNova.setTelefone1(entrada.nextLine());

        System.out.println("Telefone 2: ");
        pessoaNova.setTelefone2(entrada.nextLine());

        pessoaRepositorio.salvar(pessoaNova);

        System.out.println("Pessoa cadastrada! ");

    }

    public static void buscarUsuario() {

        System.out.println("Digite o nome do contato a ser pesquisado: ");
        String nome = entrada.nextLine();

        List<Pessoa> pessoas = pessoaRepositorio.buscarPorNome(nome);
        for (int i = 0; i< pessoas.size(); i++) {
            System.out.println(pessoas.get(i));
        }
    }

    public static void buscarLivro() {
        System.out.println("Nome do livro a procurar: ");
        String titulo;
        titulo = entrada.nextLine();
        List<Livro> livros = livroRepositorio.buscarPorTitulo(titulo);
        for (int i = 0; i < livros.size(); i++) {
            System.out.println(livros.get(i));
        }
    }

}
