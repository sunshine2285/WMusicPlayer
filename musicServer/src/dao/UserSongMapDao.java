package dao;

import model.UserSongMap;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserSongMapDao {
    public ArrayList<UserSongMap> selectByUserid(int userid, int kind) throws SQLException;

    public int selectByUserSongmap(UserSongMap userSongMap) throws SQLException;

    public int insert(UserSongMap userSongMap) throws SQLException;
}
