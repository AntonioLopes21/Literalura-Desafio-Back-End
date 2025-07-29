package br.com.estudos4dev.api_books;

import br.com.estudos4dev.api_books.principal.Principal;
import br.com.estudos4dev.api_books.service.ConsumoApi;
import br.com.estudos4dev.api_books.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiBooksApplication implements CommandLineRunner {
	private final LivroService livroService;

    public ApiBooksApplication(LivroService livroService) {
        this.livroService = livroService;
    }


    public static void main(String[] args) {
		SpringApplication.run(ApiBooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi api = new ConsumoApi();
		String url = "https://gutendex.com/books?search=Middlemarch";
		String json = api.obterDados(url);
		System.out.println(json);

		Principal principal = new Principal(livroService);

		principal.exibeMenu();
	}
}
