package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.controller.Principal;
import sample.controller.Login;
import sample.model.FabricaConexoes;
import sample.model.daos.*;
import sample.services.AuthService;

import java.io.IOException;

public class Main extends Application {

    public static final String PRINCIPAL = "/fxml/Principal.fxml";
    public static final String CADASTRO_DEPENDENTE = "/fxml/CadastraDependente.fxml";
    public static final String LOGIN = "/fxml/Login.fxml";
    private static StackPane base;

    private static FabricaConexoes fabricaConexoes;
    private static PessoaDao pessoaDao;
    private static DependenteDao dependenteDao;
    private static UserDao userDao;
    private static AuthService authService;

    private void alocaDaos(){
        fabricaConexoes = new FabricaConexoes();
        pessoaDao = new JDBCPessoaDao(fabricaConexoes);
        dependenteDao = new JDBCDependenteDao(fabricaConexoes);
        userDao = new JDBCUserDao(fabricaConexoes);
    }

    private void criaServicos(){
        authService = new AuthService(userDao);
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        alocaDaos();
        criaServicos();

        base = new StackPane();
        primaryStage.setScene(new Scene(base, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE));

        mudaCena(Main.LOGIN,(aClass)->new Login(authService));

        primaryStage.show();
    }

    public static void mudaCena(String cena, Callback construtor){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent root = loader.load();

            if (base.getChildren().stream().count() > 0) {
                base.getChildren().remove(0);
            }
            base.getChildren().add(root);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void carregaPrincipal(){
        mudaCena(Main.PRINCIPAL,(aClass)-> new Principal(pessoaDao,dependenteDao));
    }



    public static void main(String[] args) {
        launch(args);
    }
}
