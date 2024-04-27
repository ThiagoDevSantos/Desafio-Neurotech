package model;


public class Endereco {
    private String cep;
    private String uf;
    private String cidade;
    private String vizinhanca;
    private String rua;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getVizinhanca() {
        return vizinhanca;
    }

    public void setVizinhanca(String vizinhanca) {
        this.vizinhanca = vizinhanca;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Endereco(String cep, String uf, String cidade, String vizinhanca, String rua) {
        this.cep = cep;
        this.uf = uf;
        this.cidade = cidade;
        this.vizinhanca = vizinhanca;
        this.rua = rua;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", uf='" + uf + '\'' +
                ", cidade='" + cidade + '\'' +
                ", vizinhanca='" + vizinhanca + '\'' +
                ", rua='" + rua + '\'' +
                '}';
    }

    public Endereco() {

    }
}
