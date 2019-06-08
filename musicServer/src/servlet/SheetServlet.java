package servlet;

import com.google.gson.Gson;
import dao.impl.SheetDaoImpl;
import dao.impl.SheetMapDaoImpl;
import dao.impl.SongDaoImpl;
import model.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SheetServlet", urlPatterns = "/sheet")
public class SheetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<Song> songs = new ArrayList<Song>();
        try {
            ArrayList<Integer> songidlist = new SheetMapDaoImpl().seletctBySheetId(id);
            for (int songid : songidlist) {
                songs.add(new SongDaoImpl().selectById(songid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(songs));
        out.flush();
    }
}
