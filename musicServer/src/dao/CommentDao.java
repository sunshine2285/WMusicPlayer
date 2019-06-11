package dao;

import model.Comment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommentDao {
    public ArrayList<Comment> selectBySongid(int songid) throws SQLException;

    public int insert(Comment comment) throws SQLException;
    public int update(int id) throws SQLException;
    public int delete(int id) throws SQLException;
}
