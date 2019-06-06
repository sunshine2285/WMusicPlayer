package dao.impl;

import dao.UserDao;
import model.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User selectById(int id) throws SQLException {
        String sql = "select name ,avatarurl from user where id = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        ResultSet resultSet = pst.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setName(resultSet.getString(1));
            user.setAvatarUrl(resultSet.getString(2));
        }
        DBUtil.close(resultSet, pst, conn);
        return user;
    }

    @Override
    public int selectIdByOpenid(String openid) throws SQLException {
        String sql = "select id from user where openid = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, openid);

        ResultSet resultSet = pst.executeQuery();
        int result = -1;
        if (resultSet.next()) {
           result = resultSet.getInt(1);
        }
        DBUtil.close(resultSet, pst, conn);
        return result;
    }

    @Override
    public User selectByOpenid(String openid) throws SQLException {
        String sql = "select id, name ,avatarurl from user where openid = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, openid);

        ResultSet resultSet = pst.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setid(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setAvatarUrl(resultSet.getString(3));
        }
        DBUtil.close(resultSet, pst, conn);
        return user;
    }

    @Override
    public int insert(User user) throws SQLException {
        String sql = "insert into user (openid, name, avatarUrl) values(?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, user.getOpenid());
        pst.setString(2, user.getName());
        pst.setString(3, user.getAvatarUrl());

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDaoImpl();
//        System.out.println(userDao.selectById(1));
//        System.out.println(userDao.selectById(2));
//        System.out.println(userDao.selectByOpenid("123123"));
        System.out.println(userDao.selectByOpenid("1233"));
    }
}
