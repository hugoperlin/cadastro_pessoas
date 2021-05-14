package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.services.AuthService;
import sample.Main;

import java.sql.SQLException;

public class Login {


    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    private AuthService authService;

    public Login(AuthService authService){
        this.authService = authService;
    }

    @FXML
    private void login(){

        String username = tfUsername.getText();
        String password =tfPassword.getText();

        String msg = "";
        try{
            if(authService.login(username,password)){
                Main.carregaPrincipal();
                return;
            }else{
                msg = "Usuário ou senha inválidos!";
            }
        }catch (SQLException e){
            msg = e.getMessage();
        }

        Alert alert = new Alert(Alert.AlertType.ERROR,msg);
        alert.showAndWait();
    }


}
