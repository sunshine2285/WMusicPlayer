package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import dao.impl.SheetDaoImpl;
import dao.impl.SongDaoImpl;
import model.Sheet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import vo.IndexData;
import vo.Sheetitem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IndexData indexData = new IndexData();
        try {
            indexData.setRecommendSheetlist(new SheetDaoImpl().selectSheetitem(4));
            indexData.setHotSonglist(new SongDaoImpl().selectOrderSong(9,0));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(indexData));
        out.flush();
    }
}
