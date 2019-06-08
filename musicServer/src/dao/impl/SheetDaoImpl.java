package dao.impl;

import dao.SheetDao;
import model.Sheet;
import org.json.JSONArray;
import org.json.JSONObject;
import util.DBUtil;
import vo.Sheetitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SheetDaoImpl implements SheetDao {
    @Override
    public Sheet selectById(int id) throws SQLException {
        String sql = "select * from sheet where id = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        ResultSet resultSet = pst.executeQuery();
        Sheet sheet = null;
        if (resultSet.next()) {
            sheet = new Sheet();
            sheet.setid(resultSet.getInt(1));
            sheet.setName(resultSet.getString(2));
            sheet.setUserid(resultSet.getInt(3));
            sheet.setCoverUrl(resultSet.getString(4));
            sheet.setDate(resultSet.getString(5));
            sheet.setHot(resultSet.getInt(6));
        }
        DBUtil.close(resultSet, pst, conn);
        return sheet;
    }

    @Override
    public ArrayList<Sheetitem> selectSheetitem(int count) throws SQLException {
        String sql = "select * from sheet order by hot desc";
        if (count != -1)
            sql += " limit " + count;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        ArrayList<Sheetitem> sheetitemArrayList = new ArrayList<Sheetitem>();
        while (resultSet.next()) {
            Sheetitem sheetitem = new Sheetitem();
            sheetitem.setId(resultSet.getInt(1));
            sheetitem.setName(resultSet.getString(2));
            sheetitem.setUserid(resultSet.getInt(3));
            sheetitem.setCoverUrl(resultSet.getString(4));
            sheetitem.setDate(resultSet.getString(5));
            sheetitemArrayList.add(sheetitem);
        }
        DBUtil.close(resultSet, pst, conn);

        for (int i = 0; i < sheetitemArrayList.size(); ++i) {
            sheetitemArrayList.get(i).setTopThreeSonglist(
                    selectTopThreeSongById(sheetitemArrayList.get(i).getId())
            );
        }

        return sheetitemArrayList;
    }

    @Override
    public ArrayList<String> selectTopThreeSongById(int sheetid) throws SQLException {
        String sql = "select `name`, singer from song where id in " +
                "(SELECT t.songid from (select songid from sheetmap WHERE sheetid = ? LIMIT 3) as t)";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, sheetid);

        ResultSet resultSet = pst.executeQuery();
        ArrayList<String> songInfolist = new ArrayList<String>();
        while (resultSet.next()) {
            String songinfo = resultSet.getString(1) + " - " + resultSet.getString(2);
            songInfolist.add(songinfo);
        }
        DBUtil.close(resultSet, pst, conn);

        return songInfolist;
    }

    @Override
    public int insert(Sheet sheet) throws SQLException {
        String sql = "insert into sheet (name, userid, coverUrl, date, hot) values(?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, sheet.getName());
        pst.setInt(2, sheet.getUserid());
        pst.setString(3, sheet.getCoverUrl());
        pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pst.setInt(5, 0);

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    public static void main(String[] args) throws Exception {

        ArrayList<Sheetitem> recommendSheetitemlist = new SheetDaoImpl().selectSheetitem(3);
        JSONObject indexData = new JSONObject(recommendSheetitemlist);
        JSONArray recommendSheetlist = new JSONArray();
        indexData.toJSONArray(recommendSheetlist);

        System.out.println(recommendSheetlist.toString());
    }
}
