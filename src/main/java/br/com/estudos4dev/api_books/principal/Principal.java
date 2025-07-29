package br.com.estudos4dev.api_books.principal;



import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.service.ConsumoApi;
import br.com.estudos4dev.api_books.service.LivroService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private final String ENDERECO = "https://gutendex.com/books";

    //service
    private final LivroService livroService;

    public Principal(LivroService livroService) {
        this.livroService = livroService;
    }


    public void exibeMenu() throws IOException, InterruptedException {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    Bem vindo a lovraria (Love + ela ria)
                    
                    1 - Buscar livro pelo título 
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    6 - Exibir dados gerais da api
                             
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do livro:");
                    String titulo = leitura.nextLine();
                    System.out.println(encontrarPorTitulo(titulo));
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmUmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmUmDeterminadoIdioma();
                    break;

                case 6:
                    System.out.println(exibirDadosGeraisApiTeste());
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private String exibirDadosGeraisApiTeste() throws IOException, InterruptedException {
        return consumo.obterDados(ENDERECO);
    }

    private void listarLivrosEmUmDeterminadoIdioma() {
    }

    private void listarAutoresVivosEmUmDeterminadoAno() {
    }

    private void listarAutoresRegistrados() {
    }

    private void listarLivrosRegistrados() {
    }

    public Optional<Livro> encontrarPorTitulo(String titulo) {
        return livroService.encontrarPorTituloIgnoreCase(titulo);
    }


}