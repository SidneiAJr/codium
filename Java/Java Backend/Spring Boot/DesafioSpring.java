package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final RestTemplate restTemplate;

	// Injeta o RestTemplate via construtor
	public DemoApplication(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public void run(String... args) {
		Scanner entradainfo = new Scanner(System.in);
		int op = 0;

		while (op != 5) {
			System.out.println("Bem vindo ao sistema da Biblioteca:");
			System.out.println("Por Favor Selecione uma Opcao");
			System.out.println("1 - Ver catálogo");
			System.out.println("2 - Buscar Livro");
			System.out.println("3 - Lista de Autor");
			System.out.println("4 - Livros em outro Idioma");
			System.out.println("5 - Sair");
			op = entradainfo.nextInt();
			entradainfo.nextLine(); // Limpa quebra de linha

			switch (op) {
				case 1 -> vercatalogo();
				case 2 -> buscarlivro(entradainfo);
				case 3 -> listarautor();
				case 4 -> livroidioma();
				case 5 -> System.out.println("Encerrando...");
				default -> System.out.println("Opção inválida.");
			}
		}
	}

	private void vercatalogo() {
		String[] livros = {
				"Autor: Pedro Luz, Ano: 2022, Idioma: Português, Avaliação: 4",
				"Autor: Pedro Luz Jr, Ano: 2023, Idioma: Português, Avaliação: 2",
				"Autor: Pedro Klaus, Ano: 2020, Idioma: Português, Avaliação: 1.5",
				"Autor: Pedro Luis, Ano: 2019, Idioma: Português, Avaliação: 3",
				"Autor: Pedro Miguel, Ano: 2016, Idioma: Português, Avaliação: 2.5"
		};
		for (String livro : livros) {
			System.out.println(livro);
		}
	}

	private void buscarlivro(Scanner sc) {
		System.out.print("Digite o nome do livro ou autor para buscar: ");
		String termo = sc.nextLine();

		String url = "https://gutendex.com/books/?search=" + termo;

		RespostaAPI resposta = restTemplate.getForObject(url, RespostaAPI.class);

		if (resposta != null && resposta.getResults() != null) {
			System.out.println("Resultados encontrados:");
			for (Livro livro : resposta.getResults()) {
				System.out.println("Título: " + livro.getTitle());
				System.out.println("Autor(es): " + livro.getAuthors().stream().map(Autor::getName).toList());
				System.out.println("Idioma(s): " + livro.getLanguages());
				System.out.println("---------------");
			}
		} else {
			System.out.println("Nenhum livro encontrado.");
		}
	}

	private void listarautor() {
		System.out.println("Função de listar autor ainda não implementada.");
	}

	private void livroidioma() {
		System.out.println("Função de buscar por idioma ainda não implementada.");
	}

	// CLASSES INTERNAS

	static class Livro {
		private String title;
		private List<Autor> authors;
		private List<String> languages;

		public String getTitle() {
			return title;
		}

		public List<Autor> getAuthors() {
			return authors;
		}

		public List<String> getLanguages() {
			return languages;
		}
	}

	static class Autor {
		private String name;

		public String getName() {
			return name;
		}
	}

	static class RespostaAPI {
		private List<Livro> results;

		public List<Livro> getResults() {
			return results;
		}
	}
}
