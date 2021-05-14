package sample.model.daos;

import sample.model.User;

import java.sql.SQLException;

public interface UserDao {

    User login(String username, String password) throws SQLException;

}
