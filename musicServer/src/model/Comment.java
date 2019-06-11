package model;

public class Comment {
    private int id;
    private int songid;
    private int userid;
    private String content;
    private String date;
    private int thumbUp;

    public Comment(){

    }

    public Comment(Comment comment) {
        this.id = comment.getid();
        this.songid = comment.getSongid();
        this.userid = comment.getUserid();
        this.content = comment.getContent();
        this.date = comment.getDate();
        this.thumbUp = comment.getThumbUp();
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", songid=" + songid +
                ", userid=" + userid +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", thumbUp=" + thumbUp +
                '}';
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(int thumbUp) {
        this.thumbUp = thumbUp;
    }
}

