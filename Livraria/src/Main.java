import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        ExcluirLivro el = new ExcluirLivro();
        InserirLivro il = new InserirLivro();
        PesquisaLivro pl = new PesquisaLivro();
        ConexaoPostgre cp = new ConexaoPostgre();

        int opcao = 0;

        String id_isbn, nm_titulo;
        int id_categoria, id_editora;
        double vl_preco;

        System.out.println("Selecione uma das seguintes opções: \n");
        System.out.println("<1>Cadastrar Livro");
        System.out.println("<2>Pesquisar Livro por Preço");
        System.out.println("<3>Pesquisar Livro por Titulo");
        System.out.println("<4>Excluir Livro");
        System.out.println("<5>Sair");
        System.out.println();

        System.out.println("Qual a sua escolha?");
        opcao = sc.nextInt();

        switch (opcao){
            case 1:

                System.out.println("Digite o isbn: ");
                sc.nextLine();
                id_isbn = sc.nextLine();
                System.out.println("Digite o id da categoria: ");
                id_categoria = sc.nextInt();
                System.out.println("Digite o id da editora: ");
                id_editora = sc.nextInt();
                System.out.println("Digite o titulo do livro: ");
                sc.nextLine();
                nm_titulo = sc.nextLine();
                System.out.println("Digite o valor do livro: ");
                vl_preco = sc.nextDouble();

                il.inserirLivro(id_isbn, id_categoria, id_editora, nm_titulo, vl_preco);
                break;

            case 2:
                System.out.println("Digite um valor para saber quais livros estão nessa faixa de preço ou acima: ");
                vl_preco = sc.nextDouble();
                pl.pesquisaLivroPreco(vl_preco);
                break;
            case 3:
                System.out.println("Digite o nome do livro que deseja pesquisar");
                sc.nextLine();
                nm_titulo = sc.nextLine();
                pl.pesquisaLivroTitulo(nm_titulo);
                break;
            case 4:
                System.out.println("Digite o id isbn do livro que deseja excluir");
                sc.nextLine();
                id_isbn = sc.nextLine();
                el.excluirLivro(id_isbn);
                break;
            case 5:
                System.out.println("Você saiu do sistema!");
                break;

        }

    }
}