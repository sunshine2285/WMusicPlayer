package dao.impl;

import dao.SongDao;
import model.Song;
import model.UserSongMap;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SongDaoImpl implements SongDao {
    @Override
    public Song selectById(int id) throws SQLException {
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
    public int selectByNameAndSinger(String name, String singer) throws SQLException {
        String sql = "select id from song where name = ? and singer = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, name);
        pst.setString(2, singer);

        ResultSet resultSet = pst.executeQuery();
        int result = -1;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        DBUtil.close(resultSet, pst, conn);
        return result;
    }

    /**
     * 根据时间最新或者热度最高一词挑选出前 count 首歌曲
     *
     * @param count
     * @param mode  （0-代表时间 / 非0-代表热度）
     * @return ArrayList<Song>
     * @throws SQLException
     */
    @Override
    public ArrayList<Song> selectOrderSong(int count, int mode) throws SQLException {
        String sql = "select * from song order by " + (mode == 0 ? "date" : "hot") + " desc limit " + count;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        ArrayList<Song> songArrayList = new ArrayList<Song>();
        while (resultSet.next()) {
            Song song = new Song();
            song.setid(resultSet.getInt(1));
            song.setName(resultSet.getString(2));
            song.setSinger(resultSet.getString(3));
            song.setCoverUrl(resultSet.getString(4));
            song.setAudioUrl(resultSet.getString(5));
            songArrayList.add(song);
        }
        DBUtil.close(resultSet, pst, conn);
        return songArrayList;
    }

    @Override
    public ArrayList<Song> selectUserSong(int userid, int kind) throws SQLException {
        ArrayList<Integer> songidlist = new UserSongMapDaoImpl().selectByUserid(userid, kind);
        ArrayList<Song> songlist = new ArrayList<Song>();

        for (int songid : songidlist) {
            songlist.add(selectById(songid));
        }
        return songlist;
    }

    @Override
    public int insert(Song song) throws SQLException {
        if (selectByNameAndSinger(song.getName(), song.getSinger()) != -1)
            return -1;
        String sql = "insert into song (name, singer, coverUrl, audioUrl, date, hot) values(?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, song.getName());
        pst.setString(2, song.getSinger());
        pst.setString(3, song.getCoverUrl());
        pst.setString(4, song.getAudioUrl());
        pst.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pst.setInt(6, 0);

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    public static void main(String[] args) throws SQLException {
        SongDao songDao = new SongDaoImpl();
//        Song song = new Song();
//        song.setName("hello");
//        song.setSinger("sunshine");
//        song.setCoverUrl("12321");
//        song.setAudioUrl("aaaa");
//
//        int result = songDao.insert(song);
//        System.out.println(result);
//        System.out.println(songDao.SelectById(1));
//        System.out.println(songDao.selectByNameAndSinger("长大前", "K邵庄"));
//        System.out.println(songDao.selectOrderSong(6, 0));
        System.out.println((new SongDaoImpl().selectUserSong(11, 0)));
    }
}
