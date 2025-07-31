# 📚 Literalura

> **Desafio Alura Spring Boot Challenge** — Uma API para gerenciamento e consulta de livros e autores, consumindo dados da API pública Gutendex.

<h1>O projeto está em desenvolvimento e ainda não está 100%, faltando algumas validações que serão implementadas em breve.</h1>
1 - validação de dados recebidos, algumas foram realizadas mas tem outras que precisam seguir o padrão que foi solicitado sem caracteres especiais e nomes por extenso, por exemplo.
---

## ✨ Sobre o projeto

Literalura é uma aplicação backend desenvolvida em Java com Spring Boot, que permite buscar, salvar e listar livros e autores, incluindo filtros por idioma e autores vivos em um ano específico. O projeto consome dados da API externa Gutendex e armazena informações localmente para consultas rápidas.

---

## 🚀 Funcionalidades

- 🔎 Buscar livro por título na API externa e salvar no banco local
- 📖 Listar livros cadastrados com detalhes do autor e idiomas disponíveis
- 👩‍🎨 Listar autores cadastrados e filtrar autores vivos em um ano
- 🌐 Listar livros filtrados por idioma
- 🔄 Conversão entre entidades e DTOs para manipulação eficiente dos dados

---

## 🛠 Tecnologias utilizadas

| Tecnologia       | Versão / Uso                          |
| ---------------- | ----------------------------------- |
| Java             | 17                                |
| Spring Boot      | Framework principal                  |
| Spring Data JPA  | Persistência com Hibernate           |
| Banco de Dados   | PostgreSQL / H2 (testes)     |
| Jackson          | Processamento de JSON                |
| API Externa      | [Gutendex](https://gutendex.com/)   |
| Build            | Maven                      |

---
<h2>Para utilização do banco de dados H2 como demonstração vá em applications.properties </h2>
comente os campos que utilizam o postgres e descomente o h2
<img width="676" height="420" alt="image" src="https://github.com/user-attachments/assets/16186161-f739-45c6-974e-3e609b59e167" />

<h2>Agora vá no pom xml</h2>
Comente a dependência do postgres e descomente a do h2
<img width="632" height="283" alt="image" src="https://github.com/user-attachments/assets/587b085c-a525-4171-bf80-e33a92a144e7" />

⚙️ Como executar o projeto
Clone o repositório

bash
Copiar
Editar
git clone https://github.com/seu_usuario/literalura.git
cd literalura
Configure o banco de dados

Ajuste as configurações do banco (ex: MySQL, PostgreSQL ou H2) em src/main/resources/application.properties ou application.yml.
