package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

class SongCover {
    private int id;
    private String coverUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

public class UpdateCoverUrlUtil {

    public static ArrayList<SongCover> select() throws Exception {
        String sql = "select id, coverUrl from song ";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        ResultSet resultSet = pst.executeQuery();
        ArrayList<SongCover> songCovers = new ArrayList<SongCover>();
        while (resultSet.next()) {
            SongCover songCover = new SongCover();
            songCover.setId(resultSet.getInt(1));
            songCover.setCoverUrl(resultSet.getString(2));
            /**
             * 去除coverUrl后缀max_age
             *      条件：songCover.getCoverUrl().endsWith("jp")
             *  去除coverUrl-404
             *      条件：HttpsRequestUtil.testExist(songCover.getCoverUrl()) == -1
             */

            if (HttpsRequestUtil.testExist(songCover.getCoverUrl()) == -1) {
                songCovers.add(songCover);
            }
        }
        DBUtil.close(resultSet, pst, conn);
        return songCovers;
    }


    public static int update(SongCover songCover) throws Exception {
        String sql = "update song set coverUrl = ? where id = ? ";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, songCover.getCoverUrl().replace("?max_age……", ""));
        pst.setInt(2, songCover.getId());

        return pst.executeUpdate();
    }

    public static int delete(int id) throws Exception {
        String sql = "delete from song where id = ? ";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, id);
        int result = pst.executeUpdate();

        sql = "delete from sheetmap where songid = ? ";
        conn = DBUtil.getConnection();
        pst = conn.prepareStatement(sql);

        pst.setInt(1, id);

        return pst.executeUpdate() + result;

    }

    public static void main(String[] args) throws Exception {

        /**
         * 去除max_age
         */
//        ArrayList<SongCover> songCovers = select();
//        for (SongCover songCover : songCovers) {
//            System.out.println(update(songCover));
//        }

        /**
         * 去除coverUrl无法显示的
         */
//        ArrayList<SongCover> songCovers = select();
//        for (SongCover songCover : songCovers) {
//            System.out.println(delete(songCover.getId()));
//        }
    }
}
