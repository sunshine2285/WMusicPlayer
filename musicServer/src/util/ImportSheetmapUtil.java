package util;

import dao.SheetMapDao;
import dao.impl.SheetMapDaoImpl;
import model.SheetMap;

import java.sql.SQLException;

public class ImportSheetmapUtil {
    public static void main(String[] args) throws SQLException {
        SheetMapDao sheetMapDao = new SheetMapDaoImpl();
        SheetMap sheetMap = new SheetMap();

        for (int i = 44; i <= 63; ++i) {
            sheetMap.setSheetid(3);
            sheetMap.setSongid(i);
            System.out.println(sheetMapDao.insert(sheetMap));
        }
    }
}
