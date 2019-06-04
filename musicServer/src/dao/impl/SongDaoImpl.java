package dao.impl;

import dao.SongDao;
import model.Song;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SongDaoImpl implements SongDao {
    @Override
    public Song SelectById(int id) throws SQLException {
        String sql = "select * from song where id = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        ResultSet resultSet = pst.executeQuery();
        Song song = null;
        if (resultSet.next()) {
            song = new Song();
            song.setid(resultSet.getInt(1));
            song.setName(resultSet.getString(2));
            song.setSinger(resultSet.getString(3));
            song.setCoverUrl(resultSet.getString(4));
            song.setAudioUrl(resultSet.getString(5));
            song.setDate(resultSet.getString(6));
            song.setHot(resultSet.getInt(7));
        }
        DBUtil.close(resultSet, pst, conn);
        return song;
    }

    @Override
    public int insert(Song song) throws SQLException {
        String sql = "insert into song (name, singer, coverUrl, audioUrl, date, hot) values(?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, song.getName());
        pst.setString(2,song.getSinger());
        pst.setString(3, song.getCoverUrl());
        pst.setString(4, song.getAudioUrl());
        pst.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pst.setInt(6,0);

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    public static void main(String[] args) throws SQLException {
        SongDao songDao = new SongDaoImpl();
        Song song = new Song();
        song.setName("hello");
        song.setSinger("sunshine");
        song.setCoverUrl("12321");
        song.setAudioUrl("aaaa");

        int result = songDao.insert(song);
        System.out.println(result);
        System.out.println(songDao.SelectById(1));
    }
}
