package dao;

import model.User;

import java.sql.SQLException;

public interface UserDao {
    public User selectById(int id) throws SQLException;
    public int selectByOpenid(String openid) throws SQLException;

    public int insert(User user) throws SQLException;
}
