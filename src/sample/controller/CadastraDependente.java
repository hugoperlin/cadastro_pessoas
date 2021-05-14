package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;
import sample.model.Dependente;
import sample.model.Pessoa;
import sample.model.daos.DependenteDao;
import sample.model.daos.PessoaDao;

import java.sql.SQLException;
import java.util.List;

public class CadastraDependente {

    @FXML
    private ComboBox<Pessoa> cbPessoa;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfIdade;

    @FXML
    private ImageView imgView;

    @FXML
    private Button btImg;


    private int flag=0;

    private PessoaDao pessoaDao;
    private DependenteDao dependenteDao;

    public CadastraDependente(PessoaDao pessoaDao, DependenteDao dependenteDao){
        this.pessoaDao = pessoaDao;
        this.dependenteDao = dependenteDao;
    }


    public void initialize(){

        flag = 0;

        Image image = new Image(getClass().getResourceAsStream("/imgs/img2.png"));
        imgView.setImage(image);

        btImg.setGraphic(new ImageView(image));

        try {
            List<Pessoa> pessoas = pessoaDao.lista();

            for(Pessoa p:pessoas){
                cbPessoa.getItems().add(p);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void muda(){

        String img;
        if(flag == 0){
            img = "img2.png";
            flag = 1;
        }else{
            img = "img.png";
            flag = 0;
        }

        Image image = new Image(getClass().getResourceAsStream("/imgs/"+img));

        btImg.setGraphic(new ImageView(image));

    }


    @FXML
    private void cadastra(){

        Pessoa responsavel = cbPessoa.getValue();
        String nome = tfNome.getText();
        int idade = Integer.valueOf(tfIdade.getText());

        Dependente dependente = new Dependente(nome,idade,responsavel);


        try{
            dependenteDao.cadastrar(dependente);

            voltar();
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.showAndWait();
            return;
        }
    }

    @FXML
    private void cancelar(){
        voltar();
    }

    private void voltar(){
        Main.mudaCena(Main.PRINCIPAL, aClass -> new Principal(pessoaDao,dependenteDao));
    }


}
