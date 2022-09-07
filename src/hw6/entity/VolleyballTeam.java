package hw6.entity;

import java.util.Objects;

public class VolleyballTeam extends Team {
    private int scoreSet;

    public VolleyballTeam(String name, int won, int lose, int points, int played) {
        super(name, won, lose, points, played);
    }

    public VolleyballTeam(int scoreSet) {
        super();
        this.scoreSet = scoreSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolleyballTeam team = (VolleyballTeam) o;
        return scoreSet == team.scoreSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreSet);
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
