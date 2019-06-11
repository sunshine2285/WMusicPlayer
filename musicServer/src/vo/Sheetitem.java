package vo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Sheetitem {
    private int id;
    private String name;
    private int userid;
    private String coverUrl;
    private String date;
    private ArrayList<String> topThreeSonglist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getTopThreeSonglist() {
        return topThreeSonglist;
    }

    public void setTopThreeSonglist(ArrayList<String> topThreeSonglist) {
        this.topThreeSonglist = topThreeSonglist;
    }
}