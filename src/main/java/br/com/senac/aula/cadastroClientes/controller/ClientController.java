package br.com.senac.aula.cadastroClientes.controller;

import br.com.senac.aula.cadastroClientes.model.Client;
import br.com.senac.aula.cadastroClientes.model.Endereco;
import br.com.senac.aula.cadastroClientes.services.ClientesService;
import br.com.senac.aula.cadastroClientes.services.EnderecoService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;
import javafx.scene.control.*;

import javax.swing.*;
import java.util.List;

@Component
@FxmlView("/main.fxml")
public class ClientController {
    @FXML
    private TextField nomeCliente;
    @FXML
    private TextField emailCliente;
    @FXML
    private TextField documentoCliente;
    @FXML
    private TextField rgCliente;
    @FXML
    private TextField telefoneCliente;
    @FXML
    private TableView<Client> tabelaCliente;
    @FXML
    private TableColumn<Client, String> clnNomeCliente;
    @FXML
    private TableColumn<Client, String> clnEmailCliente;
    @FXML
    private TableColumn<Client, Integer> clnDocumentoCliente;
    @FXML
    private TableColumn<Client, String> clnTelefoneCliente;
    @FXML
    private TableColumn<Client, String> clnRGCliente;
    @FXML
    private TableColumn<Client, Integer> clnIDCliente;

    @FXML
    private TextField codigoClienteEndereco;
    @FXML
    private TextField cepEndereco;
    @FXML
    private TextField bairroEndereco;
    @FXML
    private TextField numeroEndereco;
    @FXML
    private TextField cidadeEndereco;
    @FXML
    private TextField estadoEndereco;
    @FXML
    private TableView<Endereco> tabelaEndereco;
    @FXML
    private TableColumn<Endereco, Integer> clnIDClienteEndereco;
    @FXML
    private TableColumn<Endereco, Integer> clnCepEndereco;
    @FXML
    private TableColumn<Endereco, String> clnBairroEndereco;
    @FXML
    private TableColumn<Endereco, Integer> clnNumeroEndereco;
    @FXML
    private TableColumn<Endereco, String> clnCidadeEndereco;
    @FXML
    private TableColumn<Endereco, String> clnEstadoEndereco;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabEndereco;
    @FXML
    private Button btnCadastrarEndereco;


    private int index = -1;
    private int index2 = -1;
    Endereco end = new Endereco();
    Client cli = new Client();

    //Vincular campo com a Coluna.
    @FXML
    public void initialize() {
        clnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        clnDocumentoCliente.setCellValueFactory(new PropertyValueFactory<>("documentoCliente"));
        clnRGCliente.setCellValueFactory(new PropertyValueFactory<>("rgCliente"));
        clnEmailCliente.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));
        clnTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefoneCliente"));
        clnIDCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));

        clnIDClienteEndereco.setCellValueFactory(new PropertyValueFactory<>("codigoClienteEndereco"));
        clnCepEndereco.setCellValueFactory(new PropertyValueFactory<>("cepEndereco"));
        clnBairroEndereco.setCellValueFactory(new PropertyValueFactory<>("bairroEndereco"));
        clnNumeroEndereco.setCellValueFactory(new PropertyValueFactory<>("numeroEndereco"));
        clnCidadeEndereco.setCellValueFactory(new PropertyValueFactory<>("cidadeEndereco"));
        clnEstadoEndereco.setCellValueFactory(new PropertyValueFactory<>("estadoEndereco"));

        this.carregarClientes();
        this.carregarEnderecos();
        btnCadastrarEndereco.setDisable(true);


        tabelaCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Client cli = tabelaCliente.getSelectionModel().getSelectedItem();

                    nomeCliente.setText(cli.getNomeCliente());
                    documentoCliente.setText(Integer.toString(cli.getDocumentoCliente()));
                    rgCliente.setText(cli.getRgCliente());
                    emailCliente.setText(cli.getEmailCliente());
                    telefoneCliente.setText(cli.getTelefoneCliente());
                    index = cli.getIdCliente();
                    nomeCliente.setDisable(true);
                    documentoCliente.setDisable(true);
                    btnCadastrarEndereco.setDisable(false);
                }
            }
        });

        tabelaEndereco.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Endereco end = tabelaEndereco.getSelectionModel().getSelectedItem();

                    codigoClienteEndereco.setText(Integer.toString(end.getCodigoClienteEndereco()));
                    cepEndereco.setText(end.getCepEndereco());
                    bairroEndereco.setText(end.getBairroEndereco());
                    numeroEndereco.setText(end.getNumeroEndereco());
                    cidadeEndereco.setText(end.getCidadeEndereco());
                    estadoEndereco.setText(end.getEstadoEndereco());
                    index2 = end.getCodigoClienteEndereco();
                    codigoClienteEndereco.setDisable(true);
                }
            }
        });
    }
    public void cadastrarEndereco() {
        codigoClienteEndereco.setText(Integer.toString(index));
        tabPane.getSelectionModel().select(tabEndereco);
        codigoClienteEndereco.setDisable(true);
        this.carregarClientes();
        this.LimparCampos();
        index = -1;
        if (EnderecoService.buscarEndereco(codigoClienteEndereco.getText())) {
            index2 = Integer.parseInt(codigoClienteEndereco.getText());

        }
    }


    public void executarSalvarCliente() {
        if (index == -1){
            Client cli = new Client();
        }

        //Resetar cor dos TextField.
        nomeCliente.setStyle("-fx-background-color: white;");
        documentoCliente.setStyle("-fx-background-color: white;");
        rgCliente.setStyle("-fx-background-color: white;");
        telefoneCliente.setStyle("-fx-background-color: white;");

        int c = 0;
        //Validaçao dos campos
        cli.setEmailCliente(emailCliente.getText());
        //Campo Cliente *
        if (campoVazio(nomeCliente.getText())) {
            nomeCliente.setStyle("-fx-background-color: pink;");
            c = 1;
        } else {
            cli.setNomeCliente(nomeCliente.getText());
        }

        //Campo Documento *
        if (campoVazio(documentoCliente.getText())) {
            documentoCliente.setStyle("-fx-background-color: pink;");
            c = 1;
        } else if (ClientesService.buscarClienteByDocumento(documentoCliente.getText()) && index == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta");
            alert.setHeaderText("Documento: " + documentoCliente.getText() + " já existe na base!");
            alert.show();
            c = 1;
        } else {
            try {
                cli.setDocumentoCliente(Integer.parseInt(documentoCliente.getText()));
            } catch (Exception e) {
                alertaCampoInvalido("Documento");
                documentoCliente.setStyle("-fx-background-color: pink;");
                c = 1;
            }
        }
        //Campo RG
        if (!campoVazio(rgCliente.getText())) {
            if (apenasNumeros(rgCliente.getText())) {
                cli.setRgCliente(rgCliente.getText());
            } else {
                alertaCampoInvalido("RG");
                rgCliente.setStyle("-fx-background-color: pink;");
                c = 1;
            }
        }else{
            cli.setRgCliente(rgCliente.getText());
        }
        //Campo Telefone
        if (!campoVazio(telefoneCliente.getText())) {
            if (apenasNumeros(telefoneCliente.getText())) {
                cli.setTelefoneCliente(telefoneCliente.getText());
            } else {
                alertaCampoInvalido("Telefone");
                telefoneCliente.setStyle("-fx-background-color: pink;");
                c = 1;
            }
        }else{
            cli.setTelefoneCliente(telefoneCliente.getText());
        }
        //Incluir Novo Registro
        if (c == 0 && index == -1) {
            //Alerta de Inclusao
            Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaSalvar.setTitle("Confirmaçao de Inclusao");
            alertaSalvar.setHeaderText("Deseja incluir novo registro ?");
            alertaSalvar.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    //Adicionar novo item a Lista
                    ClientesService.salvarCliente(cli);
                    this.carregarClientes();
                    this.LimparCampos();
                }
            });
        }
        //Alterar Registro
        if (c == 0 && index != -1) {
            Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaSalvar.setTitle("Confirmaçao de Alteraçao");
            alertaSalvar.setHeaderText("Deseja Alterar o registro ?");
            alertaSalvar.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    ClientesService.atualizarCliente(index, cli);
                    this.carregarClientes();
                    this.LimparCampos();
                    index = -1;
                }
            });
        }
    }

    public void executarExcluirCliente() {
        if (index != -1) {
            Alert alertaExcluir = new Alert(Alert.AlertType.CONFIRMATION);
            alertaExcluir.setTitle("Confirmaçao de Exclusão");
            alertaExcluir.setHeaderText("Deseja Excluir o registro ?");
            alertaExcluir.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    ClientesService.deletarCliente(index);
                    this.carregarClientes();
                    this.LimparCampos();
                    index = -1;
                }
            });
        }

    }

    public void executarSalvarEndereco() {
        Endereco end = new Endereco();

        //Resetar cor dos TextField.
        codigoClienteEndereco.setStyle("-fx-background-color: white;");
        cepEndereco.setStyle("-fx-background-color: white;");
        bairroEndereco.setStyle("-fx-background-color: white;");
        numeroEndereco.setStyle("-fx-background-color: white;");
        cidadeEndereco.setStyle("-fx-background-color: white;");
        estadoEndereco.setStyle("-fx-background-color: white;");

        int c = 0;
        //Validaçao dos campos
        //Codigo do Cliente

        if (campoVazio(codigoClienteEndereco.getText())) {
            codigoClienteEndereco.setStyle("-fx-background-color: pink;");
            c = 1;
        } else {
            try {
                end.setCodigoClienteEndereco(Integer.parseInt(codigoClienteEndereco.getText()));
            } catch (Exception e) {
                alertaCampoInvalido("Codigo do Cliente");
                codigoClienteEndereco.setStyle("-fx-background-color: pink;");
                c = 1;
            }
        }

        //Cep
        if (campoVazio(cepEndereco.getText())) {
            cepEndereco.setStyle("-fx-background-color: pink;");
            c = 1;
        } else if(apenasNumeros(cepEndereco.getText())) {
            end.setCepEndereco(cepEndereco.getText());
        }else{
            alertaCampoInvalido("CEP");
            cepEndereco.setStyle("-fx-background-color: pink;");
            c = 1;
        }
        //Bairro
        if (campoVazio(bairroEndereco.getText())) {
            bairroEndereco.setStyle("-fx-background-color: pink;");
            c = 1;
        } else{
            end.setBairroEndereco(bairroEndereco.getText());
        }
        //Numero
        if (campoVazio(numeroEndereco.getText())) {
            numeroEndereco.setStyle("-fx-background-color: pink;");
            c = 1;
        } else if(apenasNumeros(numeroEndereco.getText())) {
            end.setNumeroEndereco(numeroEndereco.getText());
        }else{
            alertaCampoInvalido("Numero");
            numeroEndereco.setStyle("-fx-background-color: pink;");
            c = 1;
        }
        //Cidade
        if (campoVazio(cidadeEndereco.getText())) {
            cidadeEndereco.setStyle("-fx-background-color: pink;");
            c = 1;
        } else{
            end.setCidadeEndereco(cidadeEndereco.getText());
        }
        //Estado
        if (campoVazio(estadoEndereco.getText())) {
            estadoEndereco.setStyle("-fx-background-color: pink;");
            c = 1;
        } else{
            end.setEstadoEndereco(estadoEndereco.getText());
        }
        //Incluir Novo Registro
        if (c == 0 && index2 == -1) {
            //Alerta de Inclusao
            Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaSalvar.setTitle("Confirmaçao de Inclusao");
            alertaSalvar.setHeaderText("Deseja incluir novo registro ?");
            alertaSalvar.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    //Adicionar novo item a Lista
                    EnderecoService.salvarEndereco(end);
                    this.carregarEnderecos();
                    this.LimparCamposEndereco();
                }
            });
        }
        //Alterar Registro
        if (c == 0 && index2 != -1) {
            Alert alertaSalvar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaSalvar.setTitle("Confirmaçao de Alteraçao");
            alertaSalvar.setHeaderText("Deseja Alterar o registro ?");
            alertaSalvar.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    EnderecoService.atualizarEndereco(index2, end);
                    this.carregarEnderecos();
                    this.LimparCamposEndereco();
                    index2 = -1;

                }
            });
        }
    }
    public void executarExcluirEndereco() {
        if (index2 != -1) {
            Alert alertaExcluir = new Alert(Alert.AlertType.CONFIRMATION);
            alertaExcluir.setTitle("Confirmaçao de Exclusão");
            alertaExcluir.setHeaderText("Deseja Excluir o registro ?");
            alertaExcluir.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.OK) {
                    EnderecoService.deletarEndereco(index2);
                    this.carregarEnderecos();
                    this.LimparCamposEndereco();
                    index2 = -1;
                }
            });
        }
    }
////////////////////////////////// Funcoes
    public void LimparCampos() {
        nomeCliente.setText("");
        documentoCliente.setText("");
        rgCliente.setText("");
        emailCliente.setText("");
        telefoneCliente.setText("");
        nomeCliente.setDisable(false);
        documentoCliente.setDisable(false);
        btnCadastrarEndereco.setDisable(true);
    }
    public void LimparCamposEndereco(){
        codigoClienteEndereco.setText("");
        cepEndereco.setText("");
        bairroEndereco.setText("");
        numeroEndereco.setText("");
        cidadeEndereco.setText("");
        estadoEndereco.setText("");
        codigoClienteEndereco.setDisable(false);
        btnCadastrarEndereco.setDisable(true);
    }
    public void carregarClientes() {
        try {
            tabelaCliente.getItems().remove(0, tabelaCliente.getItems().size());
            List<Client> clienteList = ClientesService.carregarCliente();
            tabelaCliente.getItems().addAll(clienteList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean apenasNumeros(String texto) {

        return texto.matches("[0-9]+");
    }
    public void carregarEnderecos() {
        try {
            tabelaEndereco.getItems().remove(0, tabelaEndereco.getItems().size());
            List<Endereco> enderecoList = EnderecoService.carregarEndereco();
            tabelaEndereco.getItems().addAll(enderecoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean campoVazio (String texto){
        if (texto == null || texto.trim().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    public void alertaCampoInvalido(String texto){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(texto+ " Invalido");
        alert.setHeaderText(texto +" Informado Invalido, Digite apenas numeros.");
        alert.showAndWait();
    }
}
