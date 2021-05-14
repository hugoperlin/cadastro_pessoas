package sample.services;

import sample.model.User;
import sample.model.daos.UserDao;

import java.sql.SQLException;

public class AuthService {

    private User logado;
    private UserDao userDao;

    public AuthService(UserDao userDao){
        this.userDao = userDao;
    }

    public boolean login(String username, String password) throws SQLException {
        logado = userDao.login(username,password);
        return logado!=null;
    }

    public User getLogado() {
        return logado;
    }
}
