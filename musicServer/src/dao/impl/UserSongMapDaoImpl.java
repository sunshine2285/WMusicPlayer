package dao.impl;

import dao.UserSongMapDao;
import model.UserSongMap;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserSongMapDaoImpl implements UserSongMapDao {
    @Override
    public ArrayList<Integer> selectByUserid(int userid, int kind) throws SQLException {
        String sql = "select songid from usersongmap where userid = ? and kind = ? order by date desc";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userid);
        pst.setInt(2, kind);

        ResultSet resultSet = pst.executeQuery();
        ArrayList<Integer> songidlist = new ArrayList<Integer>();
        while (resultSet.next()) {
            songidlist.add(resultSet.getInt(1));
        }
        return songidlist;
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

    @Override
    public int update(int id) throws SQLException {
        String sql = "update usersongmap set date = ? where id = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        pst.setInt(2, id);

        return pst.executeUpdate();
    }

    @Override
    public int delete(UserSongMap userSongMap) throws SQLException {
        String sql = "delete from usersongmap where userid = ? and songid = ? and kind = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userSongMap.getUserid());
        pst.setInt(2, userSongMap.getSongid());
        pst.setInt(3, userSongMap.getKind());

        return pst.executeUpdate();
    }

    public static void main(String[] args) throws Exception {
        UserSongMap userSongMap = new UserSongMap();
        userSongMap.setUserid(11);
        userSongMap.setSongid(52);
        userSongMap.setKind(2);
//        System.out.println(new UserSongMapDaoImpl().insert(userSongMap));
        System.out.println(new UserSongMapDaoImpl().delete(userSongMap));
    }
}
