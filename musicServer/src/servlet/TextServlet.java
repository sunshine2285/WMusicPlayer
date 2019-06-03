package servlet;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TextServlet", urlPatterns = "/text")
public class TextServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String value = request.getParameter("query");

        JSONObject jsonObject = new JSONObject();
        try
        {
            //封装json数据
            String[] strArray = {"English", "Math", "cs"};
            jsonObject.put("in", value);
            jsonObject.put("name", "sunshine");
            jsonObject.put("age", 18);
            jsonObject.put("hobby", "coding");
            jsonObject.put("course", strArray);

            //将json数据发送给浏览器
            response.getWriter().write(jsonObject.toString());
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
