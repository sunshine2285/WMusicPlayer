package servlet;

import com.google.gson.Gson;
import dao.impl.SongDaoImpl;
import vo.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UserPageServlet", urlPatterns = "/user")
public class UserPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));

        //userid为-1代表用户尚未注册，直接返回即可
        if(userid == -1)
            return;

        UserData userData = new UserData();

        try {
            userData.setRecentSonglist(new SongDaoImpl().selectUserSong(userid, 0));
            userData.setCollectedSonglist(new SongDaoImpl().selectUserSong(userid, 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(userData));
        out.flush();

    }
}
