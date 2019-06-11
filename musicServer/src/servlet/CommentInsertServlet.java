package servlet;

import com.google.gson.Gson;
import dao.impl.CommentDaoImpl;
import model.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "CommentInsertServlet", urlPatterns = "/insertComment")
public class CommentInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userid = Integer.parseInt(request.getParameter("userid"));
        int songid = Integer.parseInt(request.getParameter("songid"));
        String content = request.getParameter("content");

        Comment comment = new Comment();
        comment.setUserid(userid);
        comment.setSongid(songid);
        comment.setContent(content);
        int result = -1;
        try {
            result = new CommentDaoImpl().insert(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(result));
        out.flush();

    }
}
