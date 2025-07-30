package br.com.estudos4dev.api_books;


import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.service.ConsumoApi;
import br.com.estudos4dev.api_books.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
				System.out.println("Digite o titulo do livro:");
				LivroService.exibeMenu();
				String titulo = scanner.nextLine();

				livroService.findBookByTitle(titulo );

			} else if(opcao == 2) {
				System.out.println("Listando consumo de api:");
				System.out.println("Digite o nome do livro:");
				String nomeLivro = scanner.nextLine();

				consumo.consumirApi(nomeLivro);
			}
			else if(opcao == 6) {
				System.out.println("Listando livros na API:");
				livroService.listaDeLivros();
			}
		}

	}
}
