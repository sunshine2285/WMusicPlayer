package model;

public class Sheet {
    private int id;
    private String name;
    private int userid;
    private String coverUrl;
    private String date;
    private int hot;

    @Override
    public String toString() {
        return "Sheet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userid=" + userid +
                ", coverUrl='" + coverUrl + '\'' +
                ", date='" + date + '\'' +
                ", hot=" + hot +
                '}';
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
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

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }
}
