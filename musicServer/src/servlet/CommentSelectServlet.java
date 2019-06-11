package servlet;

import com.google.gson.Gson;
import dao.impl.CommentDaoImpl;
import dao.impl.UserDaoImpl;
import model.Comment;
import model.User;
import vo.CommentItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CommentSelectServlet", urlPatterns = "/comment")
public class CommentSelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int songid = Integer.parseInt(request.getParameter("songid"));
        ArrayList<CommentItem> commentItems = new ArrayList<CommentItem>();
        try {
            ArrayList<Comment> comments = new CommentDaoImpl().selectBySongid(songid);
            for (Comment comment: comments) {
                CommentItem commentItem = new CommentItem(comment);
                User user = new UserDaoImpl().selectById(commentItem.getUserid());
                commentItem.setUserName(user.getName());
                commentItem.setAvatarUrl(user.getAvatarUrl());
                commentItems.add(commentItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(commentItems));
        out.flush();
    }
}
