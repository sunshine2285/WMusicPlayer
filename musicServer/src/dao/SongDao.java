package dao;

import model.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongDao {
    public Song selectById(int id) throws SQLException;
    public int selectByNameAndSinger(String name, String singer) throws SQLException;

    /**
     * 根据时间最新或者热度最高一词挑选出前 count 首歌曲
     * @param count
     * @param mode （0-代表时间 / 非0-代表热度）
     * @return ArrayList<Song>
     * @throws SQLException
     */
    public ArrayList<Song> selectOrderSong(int count, int mode) throws SQLException;

    /**
     * 检索用户 [最近收听] / [我的收藏]
     * @param userid
     * @param kind （0-最近收听 1-我的收藏）
     * @return ArrayList<Song>
     * @throws SQLException
     */
    public ArrayList<Song> selectUserSong(int userid, int kind) throws SQLException;

    public int insert(Song song) throws SQLException;
}
