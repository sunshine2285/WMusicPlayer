package model;

public class SheetMap {
    public int id;
    public int sheetid;
    public int songid;

    @Override
    public String toString() {
        return "SheetMap{" +
                "id=" + id +
                ", sheetid=" + sheetid +
                ", songid=" + songid +
                '}';
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getSheetid() {
        return sheetid;
    }

    public void setSheetid(int sheetid) {
        this.sheetid = sheetid;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }
}
