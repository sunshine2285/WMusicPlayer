package vo;

import model.Song;

import java.util.ArrayList;

public class IndexData {
    private ArrayList<Sheetitem> recommendSheetlist;
    private ArrayList<Song> hotSonglist;

    public ArrayList<Sheetitem> getRecommendSheetlist() {
        return recommendSheetlist;
    }

    public void setRecommendSheetlist(ArrayList<Sheetitem> recommendSheetlist) {
        this.recommendSheetlist = recommendSheetlist;
    }

    public ArrayList<Song> getHotSonglist() {
        return hotSonglist;
    }

    public void setHotSonglist(ArrayList<Song> hotSonglist) {
        this.hotSonglist = hotSonglist;
    }
}
