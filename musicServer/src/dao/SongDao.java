package dao;

import model.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SongDao {
    public Song selectById(int id) throws SQLException;
    public int selectByNameAndSinger(String name, String singer) throws SQLException;
    public ArrayList<Song> selectOrderSong(int count, int mode) throws SQLException;

    public int insert(Song song) throws SQLException;
}
