package sample.model.daos;

import sample.model.Dependente;
import sample.model.FabricaConexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDependenteDao implements DependenteDao{

    private FabricaConexoes fabricaConexoes;

    public JDBCDependenteDao(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }


    @Override
    public boolean cadastrar(Dependente dependente) throws SQLException {

        Connection conn = fabricaConexoes.getConnection();

        String sql = "INSERT INTO dependente(nome,idade,pessoa_id) VALUES (?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,dependente.getNome());
        pstmt.setInt(2,dependente.getIdade());
        pstmt.setInt(3,dependente.getPessoa().getId());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return true;

    }
}
