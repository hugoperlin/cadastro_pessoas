package sample.model.daos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.FabricaConexoes;
import sample.model.Pessoa;

import java.sql.*;

public class JDBCPessoaDao implements PessoaDao{

    private FabricaConexoes fabricaConexoes;

    public JDBCPessoaDao(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }



    @Override
    public boolean cadastrar(Pessoa p) throws SQLException {
        Connection conn = fabricaConexoes.getConnection();

        String sql = "INSERT INTO pessoas(nome,idade,altura) VALUES (?,?,?)";
        //String sql2 = "INSERT INTO pessoas(nome,idade,altura) VALUES('"+p.getNome()+"',"+p.getIdade()+","+p.getAltura()+")";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        //"Hugo, 36, 1.80"

        pstmt.setString(1,p.getNome());
        pstmt.setInt(2,p.getIdade());
        pstmt.setDouble(3,p.getAltura());

        //INSERT INTO pessoas(nome,idade,altura) VALUES ('Hugo',36,1.80)


        pstmt.executeUpdate();

        pstmt.close();
        conn.close();

        return true;
    }

    @Override
    public ObservableList<Pessoa> lista() throws SQLException {
        ObservableList<Pessoa> lista = FXCollections.observableArrayList();

        Connection conn = fabricaConexoes.getConnection();

        String sql = "SELECT * FROM pessoas";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            int idade = rs.getInt("idade");
            double altura = rs.getDouble("altura");

            Pessoa p = new Pessoa(id,nome,idade,altura);

            lista.add(p);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return lista;
    }
}
