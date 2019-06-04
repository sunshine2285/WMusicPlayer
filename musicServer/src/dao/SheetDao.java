package dao;

import model.Sheet;

import java.sql.SQLException;

public interface SheetDao {
    public Sheet selectById(int id) throws  SQLException;

    public int insert(Sheet sheet) throws SQLException;
}
