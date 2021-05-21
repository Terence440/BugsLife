package assignment;

public class Reaction {
    private String reactionType;
    private int reactionCount = 0;

    public Reaction(String reactionType) {
        this.reactionType = reactionType;
    }
    public void increaseCount(){
        reactionCount++;
    }

    public String getReactionName() {
        return reactionType;
    }

    public int getReactionNum() {
        return reactionCount;
    }
    
}
