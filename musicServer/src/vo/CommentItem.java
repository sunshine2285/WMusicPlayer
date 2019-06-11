package vo;

import model.Comment;

public class CommentItem  extends Comment {
    private String userName;
    private String avatarUrl;

    public CommentItem(){

    }

    public CommentItem(Comment comment){
        super(comment);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
