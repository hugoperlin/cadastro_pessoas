package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.model.Pessoa;
import sample.model.daos.DependenteDao;
import sample.model.daos.JDBCPessoaDao;
import sample.model.daos.PessoaDao;

import java.sql.SQLException;

public class Principal {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfIdade;

    @FXML
    private TextField tfAltura;


    @FXML
    private TableView<Pessoa> tvwPessoas;

    @FXML
    private TableColumn<Pessoa, Integer> tbId;

    @FXML
    private TableColumn<Pessoa,String> tbNome;

    @FXML
    private TableColumn<Pessoa,Integer> tbIdade;

    @FXML
    private TableColumn<Pessoa,Double> tbAltura;


    private PessoaDao pessoaDao;
    private DependenteDao dependenteDao;

    public Principal(PessoaDao pessoaDao, DependenteDao dependenteDao){
        this.pessoaDao = pessoaDao;
        this.dependenteDao = dependenteDao;
    }


    public void initialize(){
        tbId.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("id"));
        tbNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
        tbIdade.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("idade"));
        tbAltura.setCellValueFactory(new PropertyValueFactory<Pessoa, Double>("altura"));

        atualizaTableView();

    }




    @FXML
    private void cadastrar(){

        String nome = tfNome.getText();

        if(nome.length() < 3){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Valor inválido para nome, tem que ter mais de 10 caracteres!");
            alert.showAndWait();
            return;
        }

        int idade=0;
        try{
            idade = Integer.valueOf(tfIdade.getText());
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Valor inválido para idade");
            alert.showAndWait();
            return;
        }

        double altura = Double.valueOf(tfAltura.getText());

        Pessoa p = new Pessoa(nome,idade,altura);

        try{
            if(pessoaDao.cadastrar(p)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Cadastro com sucesso!!");
                alert.showAndWait();
            }
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.showAndWait();
        }

        atualizaTableView();
    }

    @FXML
    private void adicionarDependente(){

        Main.mudaCena(Main.CADASTRO_DEPENDENTE,(aClass)->new CadastraDependente(pessoaDao,dependenteDao));

    }


    private void atualizaTableView(){
        try{
            ObservableList<Pessoa> lista = pessoaDao.lista();

            tvwPessoas.setItems(lista);

        }catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.showAndWait();
            return;
        }
    }


}
