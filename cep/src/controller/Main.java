package controller;

import model.Endereco;
import repository.EnderecoRepository;
import service.EnderecoService;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final EnderecoService enderecoService = new EnderecoService(new EnderecoRepository());

    public static void main(String[] args) {
        configurarLogger();
        buscarEnderecoPorCep();
    }

    private static void configurarLogger() {
        LOGGER.setLevel(Level.ALL);
    }

    private static void buscarEnderecoPorCep() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Digite o CEP: ");
            String cep = scanner.nextLine();

            Endereco endereco = enderecoService.buscarEnderecoPorCep(cep);

            if (endereco != null) {
                LOGGER.info("Endereço retornado: CEP: " + endereco.getCep() +
                        ", UF: " + endereco.getUf() +
                        ", Cidade: " + endereco.getCidade() +
                        ", Vizinhança: " + endereco.getVizinhanca() +
                        ", Rua: " + endereco.getRua());
            } else {
                LOGGER.warning("Falha ao buscar o endereço para o CEP: " + cep);
            }
        } catch (Exception e) {
            LOGGER.severe("Ocorreu um erro ao buscar o endereço: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
