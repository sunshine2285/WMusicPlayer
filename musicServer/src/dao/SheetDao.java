package dao;

import model.Sheet;
import vo.Sheetitem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SheetDao {
    public Sheet selectById(int id) throws  SQLException;
    public ArrayList<Sheetitem> selectSheetitem(int count) throws SQLException;

    //用于查询由歌单前三首歌的‘歌名-歌手’字符串组成的列表
    public ArrayList<String> selectTopThreeSongById(int sheetid) throws SQLException;

    public int insert(Sheet sheet) throws SQLException;
}
