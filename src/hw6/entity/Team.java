package hw6.entity;

public abstract class Team {
    private String name;
    private int won;
    private int lose;
    private int points;

    public Team() {
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    private int played;

    public Team(String name, int won, int lose, int points,int played) {
        this.name = name;
        this.won = won;
        this.lose = lose;
        this.points = points;
        this.played = played;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public Team(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }
}
