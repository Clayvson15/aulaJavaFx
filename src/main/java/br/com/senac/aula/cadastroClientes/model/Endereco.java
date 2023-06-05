package br.com.senac.aula.cadastroClientes.model;

public class Endereco {

private Integer codigoClienteEndereco;
private String cepEndereco;
private String bairroEndereco;
private String numeroEndereco;
private String cidadeEndereco;
private String estadoEndereco;

    public Endereco() {
    }

    public Integer getCodigoClienteEndereco() {
        return codigoClienteEndereco;
    }

    public void setCodigoClienteEndereco(Integer codigoClienteEndereco) {
        this.codigoClienteEndereco = codigoClienteEndereco;
    }

    public String getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(String cepEndereco) {
        this.cepEndereco = cepEndereco;
    }

    public String getBairroEndereco() {
        return bairroEndereco;
    }

    public void setBairroEndereco(String bairroEndereco) {
        this.bairroEndereco = bairroEndereco;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public String getEstadoEndereco() {
        return estadoEndereco;
    }

    public void setEstadoEndereco(String estadoEndereco) {
        this.estadoEndereco = estadoEndereco;
    }

    public Endereco(Integer codigoClienteEndereco, String cepEndereco, String bairroEndereco, String numeroEndereco, String cidadeEndereco, String estadoEndereco) {
        this.codigoClienteEndereco = codigoClienteEndereco;
        this.cepEndereco = cepEndereco;
        this.bairroEndereco = bairroEndereco;
        this.numeroEndereco = numeroEndereco;
        this.cidadeEndereco = cidadeEndereco;
        this.estadoEndereco = estadoEndereco;
    }
}
