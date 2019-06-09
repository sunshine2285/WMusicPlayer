package servlet;

import com.google.gson.Gson;
import dao.impl.SongDaoImpl;
import model.Song;
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

@WebServlet(name = "SonglistServlet", urlPatterns = "/songlist")
public class SonglistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Song> songlist = new ArrayList<Song>();

        try {
            songlist = new SongDaoImpl().selectOrderSong(30,0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(songlist));
        out.flush();
    }
}
