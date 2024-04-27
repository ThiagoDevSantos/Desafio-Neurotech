# Aplicação de Endereço

Esta é uma aplicação Java que realiza automação para obter informações de endereço utilizando a API externa do Brasil Docs. A aplicação foi desenvolvida em Java 11, utilizando JDBC para interação com um banco de dados MySQL 8 e seguindo princípios de Clean Code.

## Funcionalidades
- Busca de informações de endereço por CEP.
- Integração com a API externa do Brasil Docs para obter dados de endereço.
- Armazenamento dos dados de endereço em um banco de dados MySQL.
- Exibição de dados de endereço através de logs.

## Requisitos
- Java 11
- MySQL 8
- Conexão com a internet

## Configuração
1. Certifique-se de ter o Java 11 e o MySQL 8 instalados em sua máquina.
2. Clone este repositório.
3. Configure o acesso ao banco de dados MySQL no arquivo EnderecoRepository.java, localizado no pacote repository.

## Uso
1. Execute a classe EnderecoController no pacote controller para iniciar a aplicação.
2. Insira o CEP desejado quando solicitado.
3. Os dados de endereço serão buscados na API do Brasil Docs, armazenados no banco de dados e exibidos no console.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir um pull request ou uma issue para sugerir melhorias.

## Autores
Esta aplicação foi desenvolvida por Thiago.

## Licença
Este projeto está licenciado sob a Licença MIT - consulte o arquivo LICENSE para obter detalhes.
