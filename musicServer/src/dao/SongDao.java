package dao;

import model.Song;

import java.sql.SQLException;

public interface SongDao {
    public Song SelectById(int id) throws SQLException;

    public int insert(Song song) throws SQLException;
}
