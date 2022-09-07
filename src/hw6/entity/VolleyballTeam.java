package hw6.entity;

public class VolleyballTeam extends Team {
    private int scoreSet;

    public VolleyballTeam(String name, int won, int lose, int points, int played) {
        super(name, won, lose, points, played);
    }

    public VolleyballTeam(int scoreSet) {
        super();
        this.scoreSet = scoreSet;
    }

    public int getScoreSet() {
        return scoreSet;
    }

    public void setScoreSet(int scoreSet) {
        this.scoreSet = scoreSet;
    }

    public VolleyballTeam(String name) {
        super(name);
    }
}
