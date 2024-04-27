package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Endereco;
import repository.EnderecoRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.logging.Logger;

public class EnderecoService {
    private static final String BRASIL_API_URL = "https://brasilapi.com.br/api/cep/v1/";
    private static final Logger LOGGER = Logger.getLogger(EnderecoService.class.getName());

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public Endereco buscarEnderecoPorCep(String cep) {
        try {
            String jsonEndereco = consultarApiEndereco(cep);
            if (jsonEndereco != null) {
                Endereco endereco = parseJsonToEndereco(jsonEndereco);
                salvarEnderecoNoBanco(endereco);
                return endereco;
            } else {
                LOGGER.warning("Erro ao buscar endereço. Resposta da API é nula.");
                return null;
            }
        } catch (Exception e) {
            LOGGER.severe("Erro ao buscar endereço: " + e.getMessage());
            return null;
        }
    }

    private String consultarApiEndereco(String cep) throws IOException {
        URL url = new URL(BRASIL_API_URL + cep);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            LOGGER.warning("Erro ao consultar API de endereço. Código de status: " + responseCode);
            return null;
        }
    }

    private Endereco parseJsonToEndereco(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        String cep = rootNode.get("cep").asText();
        String uf = rootNode.get("state").asText();
        String cidade = rootNode.get("city").asText();
        String vizinhanca = rootNode.get("neighborhood").asText();
        String rua = rootNode.get("street").asText();

        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setUf(uf);
        endereco.setCidade(cidade);
        endereco.setVizinhanca(vizinhanca);
        endereco.setRua(rua);

        return endereco;
    }

    private void salvarEnderecoNoBanco(Endereco endereco) {
        repository.salvarEndereco(endereco);
        LOGGER.info("Endereço salvo no banco: CEP: " + endereco.getCep() +
                ", UF: " + endereco.getUf() +
                ", Cidade: " + endereco.getCidade() +
                ", Vizinhança: " + endereco.getVizinhanca() +
                ", Rua: " + endereco.getRua());
    }

}
