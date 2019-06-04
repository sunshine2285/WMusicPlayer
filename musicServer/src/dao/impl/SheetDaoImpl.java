package dao.impl;

import dao.SheetDao;
import model.Sheet;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    public int insert(Sheet sheet) throws SQLException {
        String sql = "insert into sheet (name, userid, coverUrl, date, hot) values(?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, sheet.getName());
        pst.setInt(2, sheet.getUserid());
        pst.setString(3, sheet.getCoverUrl());
        pst.setString(4,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        pst.setInt(5,0);

        int result = pst.executeUpdate();
        DBUtil.close(null, pst, conn);
        return result;
    }

    public static void main(String[] args) throws SQLException {
        SheetDao sheetDao = new SheetDaoImpl();

        Sheet sheet = new Sheet();
        sheet.setName("sun");
        sheet.setUserid(123);
        sheet.setCoverUrl("abcd");

        System.out.println(sheetDao.insert(sheet));
        System.out.println(sheetDao.selectById(1));
    }
}
