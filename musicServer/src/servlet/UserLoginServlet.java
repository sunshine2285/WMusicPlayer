package servlet;

import dao.impl.UserDaoImpl;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;
import util.HttpsRequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserLoginServlet", urlPatterns = "/login")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        //请求微信服务端API获取用户openid
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxebb9af236307ae59&secret=02d11f05951e4b743f038f03e0e774a8&grant_type=authorization_code&js_code=" + code;
        String returnData = HttpsRequestUtil.getReturnJSONData(url, "GET");

        JSONObject userData = new JSONObject();
        try {
            String openid = new JSONObject(returnData).getString("openid");
            User user = new UserDaoImpl().selectByOpenid(openid);
            //该用户已注册
            if (user != null) {
                userData.put("userid", user.getid());
                userData.put("userName", user.getName());
                userData.put("avatarUrl", user.getAvatarUrl());
            } else {
                //用户未注册则返回用户的 userid=-1
                userData.put("userid",-1);
                userData.put("userName", "sunshine");
                userData.put("avatarUrl", "12323");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.write(userData.toString());
        out.flush();
    }
}
