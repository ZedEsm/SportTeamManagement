package hw6.entity;

public class Team {
    private int position ;
    private String club;
    private int played,won,drawn,lost,gf,ga,gd,points;

    public Team( String club, int played, int won, int drawn, int lost, int gf, int ga, int gd, int points) {
        this.club = club;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.gf = gf;
        this.ga = ga;
        this.gd = gd;
        this.points = points;
    }
    public Team(String club ){
        this.club = club;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }
    public Team(){}

    public int getGf() {
        return gf;
    }

    @Override
    public String toString() {
        return "Team{" +
                "club='" + club + '\'' +
                ", played=" + played +
                ", won=" + won +
                ", drawn=" + drawn +
                ", lost=" + lost +
                ", gf=" + gf +
                ", ga=" + ga +
                ", gd=" + gd +
                ", points=" + points +
                '}';
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getGa() {
        return ga;
    }

    public void setGa(int ga) {
        this.ga = ga;
    }

    public int getGd() {
        return gd;
    }

    public void setGd(int gd) {
        this.gd = gd;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }



}
