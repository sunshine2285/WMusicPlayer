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

    public String toJSONString(){
        JSONObject sheetitemData = new JSONObject();
        try {
            sheetitemData.put("id", id);
            sheetitemData.put("name", name);
            sheetitemData.put("userid", userid);
            sheetitemData.put("coverUrl", coverUrl);
            sheetitemData.put("date", date);
            sheetitemData.put("topThreeSonglist", topThreeSonglist);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sheetitemData.toString();
    }

    public static void main(String[] args) {
        Sheetitem sheetitem = new Sheetitem();
        sheetitem.setId(1);
        sheetitem.setName("sun");
        sheetitem.setUserid(2285);
        sheetitem.setCoverUrl("12313");
        sheetitem.setDate("2012-02-03");
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("a-b");
        strings.add("a-c");
        strings.add("a-d");
        sheetitem.setTopThreeSonglist(strings);


        JSONArray jsonArray = new JSONArray();
        jsonArray.put(sheetitem.toJSONString());
        jsonArray.put(sheetitem.toJSONString());
        jsonArray.put(sheetitem.toJSONString());
        System.out.println(jsonArray.toString());
    }
}