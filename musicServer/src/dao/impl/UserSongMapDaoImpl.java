package dao.impl;

import dao.UserSongMapDao;
import model.UserSongMap;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserSongMapDaoImpl implements UserSongMapDao {
    @Override
    public ArrayList<UserSongMap> selectByUserid(int userid, int kind) throws SQLException {
        String sql = "select songid from usersongmap where userid = ? and kind = ? ";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userid);
        pst.setInt(2, kind);

        ResultSet resultSet = pst.executeQuery();
        ArrayList<UserSongMap> userSongMaps = new ArrayList<UserSongMap>();
        while (resultSet.next()) {
            UserSongMap userSongMap = new UserSongMap();
            userSongMap.setUserid(userid);
            userSongMap.setKind(kind);
            userSongMap.setSongid(resultSet.getInt(1));
            userSongMaps.add(userSongMap);
        }
        return userSongMaps;
    }

    @Override
    public int selectByUserSongmap(UserSongMap userSongMap) throws SQLException {
        String sql = "select id from usersongmap where userid = ? and songid = ? and kind = ?  ";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userSongMap.getUserid());
        pst.setInt(2, userSongMap.getSongid());
        pst.setInt(3, userSongMap.getKind());

        ResultSet resultSet = pst.executeQuery();
        int result = -1;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        return result;
    }

    @Override
    public int insert(UserSongMap userSongMap) throws SQLException {
        String sql = "insert into usersongmap (userid, songid, kind) values(?, ?, ?)";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userSongMap.getUserid());
        pst.setInt(2, userSongMap.getSongid());
        pst.setInt(3, userSongMap.getKind());

        return pst.executeUpdate();
    }

    public static void main(String[] args) throws Exception {
        UserSongMap userSongMap = new UserSongMap();
        userSongMap.setUserid(1);
        userSongMap.setSongid(2);
        userSongMap.setKind(2);
        System.out.println(new UserSongMapDaoImpl().insert(userSongMap));
    }
}
