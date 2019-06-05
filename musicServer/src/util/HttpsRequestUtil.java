package util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsRequestUtil {


    /*请求url获取返回的内容*/
    private static String getReturn(HttpURLConnection connection) throws IOException {
        StringBuffer buffer = new StringBuffer();
        //将返回的输入流转换成字符串
        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            String result = buffer.toString();
            return result;
        }
    }

    public static String getReturnJSONData(String url, String method) {
        String result = null;
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-type", "application/json");
            conn.connect();
            result = getReturn(conn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxebb9af236307ae59&secret=02d11f05951e4b743f038f03e0e774a8&js_code=033WFIsG0DHO1e2n5drG0t9VsG0WFIsr&grant_type=authorization_code";
//        String url = "http://localhost:8080/musicServer/text";

        URL serverUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //必须设置false，否则会自动redirect到重定向后的地址
//        conn.setInstanceFollowRedirects(false);
        conn.connect();
        String result = getReturn(conn);
        JSONObject jsonObject = new JSONObject(result);
        System.out.println(jsonObject);

    }
}
