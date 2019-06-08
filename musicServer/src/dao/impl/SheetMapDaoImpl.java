package dao.impl;

import dao.SheetMapDao;
import model.SheetMap;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SheetMapDaoImpl implements SheetMapDao {
    @Override
    public ArrayList<Integer> seletctBySheetId(int sheetid) throws SQLException {
        String sql = "select songid from sheetmap where sheetid = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, sheetid);

        ResultSet resultSet = pst.executeQuery();
        ArrayList<Integer> songidArrayList = new ArrayList<Integer>();
        while (resultSet.next()) {
            songidArrayList.add(resultSet.getInt(1));
        }

        DBUtil.close(resultSet, pst, conn);
        return songidArrayList;
    }

    @Override
    public int selectBySheetIdAndSongId(int sheetid, int songid) throws SQLException {
        String sql = "select id from sheetmap where sheetid = ? and songid = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, sheetid);
        pst.setInt(2, songid);

        ResultSet resultSet = pst.executeQuery();
        int result = 0;
        if (resultSet.next()) {
           result = 1;
        }
        DBUtil.close(resultSet, pst, conn);
        return result;
    }

    @Override
    public int insert(SheetMap sheetMap) throws SQLException {
        if(selectBySheetIdAndSongId(sheetMap.getSheetid(), sheetMap.getSongid()) == 1)
            return 0;
        String sql = "insert into sheetmap (sheetid, songid) values(?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, sheetMap.getSheetid());
        pst.setInt(2, sheetMap.getSongid());

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    @Override
    public int delete(int id, int mode) throws SQLException {
        String kind = (mode == 1) ? "songid" : "sheetid";
        String sql = "delete  from sheetmap where "
                + kind +" = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    public static void main(String[] args) throws SQLException {
        SheetMapDao sheetMapDao = new SheetMapDaoImpl();

        SheetMap sheetMap = new SheetMap();
        sheetMap.setSheetid(2);
        for (int i = 0; i < 5; ++i){
            sheetMap.setSongid(i);
            System.out.println(sheetMapDao.insert(sheetMap));
        }
//        System.out.println(sheetMapDao.delete(1, 1));
//        System.out.println(sheetMapDao.delete(1,0));
//        System.out.println(sheetMapDao.delete(3,1));
//        System.out.println(sheetMapDao.delete(3,0));

//        System.out.println(sheetMapDao.seletctBySheetId(3));
    }
}
