package sample.model.daos;

import javafx.collections.ObservableList;
import sample.model.Pessoa;

import java.sql.SQLException;

public interface PessoaDao {

    //crud
       //create
       //retrieve
          //lista
          //por id
          //por nome
       //update
       //delete

    boolean cadastrar(Pessoa p) throws SQLException;
    ObservableList<Pessoa> lista() throws SQLException;

}
