package sample.model.daos;

import sample.model.Dependente;

import java.sql.SQLException;

public interface DependenteDao {

    boolean cadastrar(Dependente dependente) throws SQLException;

}
