package servlet;

import java.util.ResourceBundle;

public class test {
    public static void main(String[] args) {
        ResourceBundle resource = ResourceBundle.getBundle("config/sourcePath");
        String key = resource.getString("musicBasePath");
        System.out.println(key);
        key += "\\仓颉 - 樱海.mp3";
        System.out.println(key);
    }
}
