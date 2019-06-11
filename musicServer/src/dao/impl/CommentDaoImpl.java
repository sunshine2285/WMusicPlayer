package dao.impl;

import dao.CommentDao;
import model.Comment;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentDaoImpl implements CommentDao {
    @Override
    public ArrayList<Comment> selectBySongid(int songid) throws SQLException {
        String sql = "select * from comment where songid = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, songid);

        ResultSet resultSet = pst.executeQuery();
        ArrayList<Comment> commentArrayList = new ArrayList<Comment>();
        while (resultSet.next()) {
            Comment comment = new Comment();
            comment.setid(resultSet.getInt(1));
            comment.setSongid(resultSet.getInt(2));
            comment.setUserid(resultSet.getInt(3));
            comment.setContent(resultSet.getString(4));
            comment.setDate(resultSet.getString(5));
            comment.setThumbUp(resultSet.getInt(6));
            commentArrayList.add(comment);
        }
        DBUtil.close(resultSet, pst, conn);
        return commentArrayList;
    }

    @Override
    public int insert(Comment comment) throws SQLException {
        String sql = "insert into comment (songid, userid, content, date, thumbUp) values(?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, comment.getSongid());
        pst.setInt(2, comment.getUserid());
        pst.setString(3,comment.getContent());
        pst.setString(4,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pst.setInt(5,0);

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    @Override
    public int update(int id) throws SQLException {
        String sql = "update comment set thumbUp = thumbUp + 1 where id = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "delete  from comment where id = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    public static void main(String[] args) throws SQLException {
        CommentDao commentDao = new CommentDaoImpl();

        Comment comment = new Comment();
        comment.setSongid(1);
        comment.setUserid(5);
        comment.setContent("hello");

//        System.out.println(commentDao.insert(comment));
//        System.out.println(commentDao.update(3,-1));
        System.out.println(commentDao.delete(3));
        System.out.println(commentDao.selectBySongid(2));

    }
}
