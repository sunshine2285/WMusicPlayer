package servlet;

import dao.impl.UserDaoImpl;
import model.User;
import org.json.JSONObject;
import util.HttpsRequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserRegisterServlet", urlPatterns = "/register")
public class UserRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String userName = request.getParameter("userName");
        String avatarUrl = request.getParameter("avatarUrl");

        //请求微信服务端API获取用户openid
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxebb9af236307ae59&secret=02d11f05951e4b743f038f03e0e774a8&grant_type=authorization_code&js_code=" + code;
        String returnData = HttpsRequestUtil.getReturnJSONData(url, "GET");

        JSONObject userData = new JSONObject();
        try {
            String openid = new JSONObject(returnData).getString("openid");
            User user = new User();
            user.setOpenid(openid);
            user.setName(userName);
            user.setAvatarUrl(avatarUrl);
            new UserDaoImpl().insert(user);
            int userid = new UserDaoImpl().selectIdByOpenid(openid);
            userData.put("userid",userid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(userData.toString());
        out.flush();
    }
}
