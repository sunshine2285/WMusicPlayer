package servlet;

import com.google.gson.Gson;
import dao.impl.UserSongMapDaoImpl;
import model.UserSongMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UserCollectSongServlet", urlPatterns = "/collect")
public class UserCollectSongServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid= Integer.parseInt(request.getParameter("userid"));
        int songid= Integer.parseInt(request.getParameter("songid"));
        int kind = Integer.parseInt(request.getParameter("kind"));

        UserSongMap userSongMap = new UserSongMap();
        userSongMap.setUserid(userid);
        userSongMap.setSongid(songid);
        userSongMap.setKind(kind);

        int result = -1;
        try {
            int id = new UserSongMapDaoImpl().selectByUserSongmap(userSongMap);
            if(id == -1)
                result = new UserSongMapDaoImpl().insert(userSongMap);
            else if(kind == 0)
                result = new UserSongMapDaoImpl().update(id);
            else
                result = 0;
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(result));
        out.flush();

    }
}
