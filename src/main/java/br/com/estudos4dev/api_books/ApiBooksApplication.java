package br.com.estudos4dev.api_books;


import br.com.estudos4dev.api_books.entity.dto.RespostaApiDTO;
import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.service.ConsumoApi;
import br.com.estudos4dev.api_books.service.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.Optional;
import java.util.Scanner;
@RequiredArgsConstructor
@SpringBootApplication
public class ApiBooksApplication implements CommandLineRunner {

	@Autowired
	private final LivroService livroService;
	ConsumoApi consumo = new ConsumoApi();

    public static void main(String[] args) {
		SpringApplication.run(ApiBooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;

		while (loop) {
			LivroService.exibeMenu();

			System.out.println("Digite uma opção:");
			int opcao = scanner.nextInt();
			scanner.nextLine(); // Consumir a quebra de linha

			if(opcao == 1) {
				System.out.println("1 - buscar livro por título");
				System.out.println("Digite o titulo do livro:");
				String titulo = scanner.nextLine();

				livroService.findBookByTitleAndSave(titulo);


			} else if(opcao == 2) {
				System.out.println("2 - listar livros registrados");
				System.out.println("Digite o nome do livro:");
				String nomeLivro = scanner.nextLine();

				consumo.consumirApi(nomeLivro);
			} else if (opcao == 3) {
				System.out.println("3 - listar autores registrados");

			} else if(opcao == 4) {
				System.out.println("4 - listar autores vivos em um determinado ano");

			} else if(opcao == 5) {
				System.out.println("5 - listar livros em um determinado idioma");

			}
			else if(opcao == 6) {
				System.out.println("6 - listar livros na api");
				livroService.requisicoesApi();
			}
		}

	}
}
