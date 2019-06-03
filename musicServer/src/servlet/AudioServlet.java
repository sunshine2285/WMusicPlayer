package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;

@WebServlet(name = "AudioServlet", urlPatterns = "/audio")
public class AudioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String audioName = "仓颉 - 樱海.mp3";
        ResourceBundle resource = ResourceBundle.getBundle("config/sourcePath");
        String audioPath = resource.getString("musicBasePath") + '\\'+ audioName;

        //输出audio流
        try{
            response.setHeader("Content-Type", "audio/mpeg");
            File file = new File(audioPath);
            int len_l = (int) file.length();
            byte[] buf = new byte[2048];
            FileInputStream fis = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            len_l = fis.read(buf);

            while (len_l != -1) {
                out.write(buf, 0, len_l);
                len_l = fis.read(buf);
            }

            out.flush();
            out.close();
            fis.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
