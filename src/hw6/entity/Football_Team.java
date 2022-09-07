package hw6.entity;
public class Football_Team extends Team {
    private int drawn,gf,ga,gd;

    public Football_Team(String club, int played, int won, int drawn, int lost, int gf, int ga, int gd, int points) {
        super(club,won,lost,points,played);
        this.drawn = drawn;
        this.gf = gf;
        this.ga = ga;
        this.gd = gd;
    }


    public Football_Team(String clubName) {
        super(clubName);
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getGf() {
        return gf;
    }

    @Override
    public String toString() {
        return "Football_Team{" +
                ", played=" + getPlayed() +
                ", won=" + getWon() +
                ", drawn=" + drawn +
                ", lost=" + getLose() +
                ", gf=" + gf +
                ", ga=" + ga +
                ", gd=" + gd +
                ", points=" + getPoints() +
                ", club='" + getName() + '\'' +
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

}
