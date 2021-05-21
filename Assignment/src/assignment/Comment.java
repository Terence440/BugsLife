package assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Comment {

    private String commentUser;
    protected String commentText;
    private int commentID;
    private static int commentCount = 0;
//    private SimpleDateFormat ft;
    private Date timestampComment;
    private List<Reaction> reaction = new ArrayList<>(Arrays.asList(new Reaction("Angry"), new Reaction("Happy"), new Reaction("Smile")));

    public Comment(String commentUser, String commentText) {
        this.commentText = commentText;
        this.commentUser = commentUser;
        this.commentID = commentID;
        this.timestampComment = new Date();
        commentCount++;
        commentID = commentCount;
    }

    public void increaseReactionCount(String s) {
        int index = getIndex(s);
        Reaction r = reaction.get(index);
        r.increaseCount();
    }

    public String getAllReactionsData() {
        String s = "$$\n";
        for (int i = 0; i < reaction.size(); i++) {
            if (reaction.get(i).getReactionNum() != 0) {
                s += reaction.get(i).getReactionNum() + " people react with " + reaction.get(i).getReactionName() + "\n";
            }
        }
        return s;
    }
    
    public int getIndex(String s) {
        int index = 0;
        switch (s) {
            case "Angry" :
                index = 0;
            case "Happy" :
                index = 1;
            case "Smile" :
                index = 2;
        }
        return index;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public int getCommentID() {
        return commentID;
    }
    
    public Date getTimestamp(){
        return timestampComment;
    }
    
    /** Changed **
    public String getTimestamp() {
        ft =new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        return ft.format(timestampComment);
    }
    */
    
    public String toString() {
        String s = "#" + this.getCommentID() + "\tCreated on: " + this.getTimestamp() + "\tBy: " + this.getCommentUser() + "\n"
                + this.getCommentText() + "\n"
                + this.getAllReactionsData() + "\n";
        return s;

    }
    
    /*    
    public String getReactionType(String s) {
        int index = getIndex(s);
        Reaction r = reaction.get(index);
        return r.getReactionName();
    }

    public int getReactionCount(String s) {
        int index = getIndex(s);
        Reaction r = reaction.get(index);
        return r.getReactionNum();
    }

    
     */

}
