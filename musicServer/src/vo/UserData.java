package vo;

import model.Song;

import java.util.ArrayList;

public class UserData {
    private ArrayList<Song> recentSonglist;
    private ArrayList<Song> collectedSonglist;

    public ArrayList<Song> getRecentSonglist() {
        return recentSonglist;
    }

    public void setRecentSonglist(ArrayList<Song> recentSonglist) {
        this.recentSonglist = recentSonglist;
    }

    public ArrayList<Song> getCollectedSonglist() {
        return collectedSonglist;
    }

    public void setCollectedSonglist(ArrayList<Song> collectedSonglist) {
        this.collectedSonglist = collectedSonglist;
    }
}
