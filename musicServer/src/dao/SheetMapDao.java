package dao;

import model.SheetMap;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SheetMapDao {
    public ArrayList<Integer> seletctBySheetId(int sheetid) throws SQLException;
    public int selectBySheetIdAndSongId(int sheetid, int songid) throws SQLException;
    public int insert(SheetMap sheetMap) throws SQLException;
    public int delete(int id, int mode) throws SQLException;
}
