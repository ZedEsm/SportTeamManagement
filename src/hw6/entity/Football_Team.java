package hw6.entity;

import java.util.Objects;

public class Football_Team{
    private int position ;

    private int played,won,drawn,lost,gf,ga,gd,points;
    private String club;

    public Football_Team(String club, int played, int won, int drawn, int lost, int gf, int ga, int gd, int points) {
        this.club =club;
        this.won = won;
        this.drawn = drawn;
        this.played = played;
        this.lost = lost;
        this.gf = gf;
        this.ga = ga;
        this.gd = gd;
        this.points = points;
    }

    public Football_Team(String clubName) {
        this.club =clubName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }



    public int getPlayed() {
        return played;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
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
    public int getGf() {
        return gf;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Football_Team that = (Football_Team) o;
        return position == that.position && played == that.played && won == that.won && drawn == that.drawn && lost == that.lost && gf == that.gf && ga == that.ga && gd == that.gd && points == that.points && club.equals(that.club);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, played, won, drawn, lost, gf, ga, gd, points, club);
    }

    @Override
    public String toString() {
        return "Football_Team{" +
                "position=" + position +
                ", played=" + played +
                ", won=" + won +
                ", drawn=" + drawn +
                ", lost=" + lost +
                ", gf=" + gf +
                ", ga=" + ga +
                ", gd=" + gd +
                ", points=" + points +
                ", club='" + club + '\'' +
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
