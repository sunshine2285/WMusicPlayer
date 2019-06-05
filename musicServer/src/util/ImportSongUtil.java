package util;

import dao.SongDao;
import dao.impl.SongDaoImpl;
import model.Song;
import org.json.JSONArray;
import org.json.JSONObject;

public class ImportSongUtil {
    public static void main(String[] args)throws Exception {
        String url = "https://v1.itooi.cn/tencent/songList?id=7012831061&page=0&format=1";
        String returnData = HttpsRequestUtil.getReturnJSONData( url,"GET");
        JSONArray songlistData = new JSONArray(new JSONObject(returnData).get("data").toString());

        SongDao songDao = new SongDaoImpl();
        Song song = new Song();

        for(int i = 0; i < songlistData.length(); ++i)
        {
            JSONObject songData = new JSONObject(songlistData.getString(i));
            song.setName(songData.getString("name"));
            song.setSinger(songData.getString("singer"));
            song.setCoverUrl(songData.getString("pic"));
            song.setAudioUrl(songData.getString("url"));
            songDao.insert(song);
        }
    }
}
