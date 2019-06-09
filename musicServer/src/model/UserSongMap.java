package model;

public class UserSongMap {
    private int id;
    private int userid;
    private int songid;
    //kind=0 表示用户最近收听
    //kind=1 表示用户收藏歌曲
    private int kind;

    @Override
    public String toString() {
        return "UserSongMap{" +
                "id=" + id +
                ", userid=" + userid +
                ", songid=" + songid +
                ", kind=" + kind +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
