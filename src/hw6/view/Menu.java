package hw6.view;

import hw6.entity.Team;
import hw6.repository.FootballRepository;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Scanner;

public class Menu {
    private static final String[] options = {"1- Add Club", "2- Delete Club", "3- Add Game Between 2 Clubs", "4- View Information About Club", "5- View Table Of Composition"};
    private static final FootballRepository footballRepository = new FootballRepository();
    private static int played;
    private static int won;
    private static int drawn;
    private static int lose;
    private static int drawn2;
    private static ClubComparator clubComparator ;

    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.println("Choose your option : ");
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        printMenu(options);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter Name Of Club:");
                String clubName = scanner.next();
                Team team = new Team(clubName);
                setTeam(team.getClub());
                break;
            case 2:
                break;
            case 3:
                System.out.println("Enter Name Of Team1 And Team2: ");
                String nameTeam = scanner.next();
                String nameTeam2 = scanner.next();
                gameWithTwoTeams(nameTeam, nameTeam2);
                break;
            case 4:
                System.out.println("Enter Name Of Club:");
                String name = scanner.next();
                System.out.println(findName(name));
                break;
            case 5:

        }

    }

    public static void gameWithTwoTeams(String t1, String t2) throws Exception {
        Team firstTeam = findName(t1);
        Team secondTeam = findName(t2);
        if (firstTeam != null && secondTeam != null) {
            System.out.println("Enter Goal Of Team1:");
            int gft1 = scanner.nextInt();
            System.out.println("Enter goal Of Team2");
            int gft2 = scanner.nextInt();
            if (gft1 > gft2) {
                //setgf
                int gf1 = firstTeam.getGf();
                gf1 += gft1;
                firstTeam.setGf(gf1);
                int gf2 = secondTeam.getGf();
                gf2 += gft2;
                secondTeam.setGf(gf2);
                //setplayed
                int played1 = firstTeam.getPlayed();
                int played2 = secondTeam.getPlayed();
                played++;
                played1 += played;
                played2 += played;
                firstTeam.setPlayed(played1);
                secondTeam.setPlayed(played2);
                //setga
                int ga1 = firstTeam.getGa();
                ga1 += gft2;
                firstTeam.setGa(ga1);
                int ga2 = secondTeam.getGa();
                ga2 += gft1;
                secondTeam.setGa(ga2);
                //setgd
                int gd1 = gf1 - ga1;
                firstTeam.setGd(gd1);
                int gd2 = gf2 - ga2;
                secondTeam.setGd(gd2);
                won++;
                lose++;
                //setpoints
                int points = firstTeam.getPoints();
                points += won * 3;
                firstTeam.setPoints(points);
                //setwon
                int win = firstTeam.getWon();
                won += win;
                firstTeam.setWon(won);
                //setlost
                int lost = secondTeam.getLost();
                lose += lost;
                secondTeam.setLost(lose);
            } else if (gft1 == gft2) {
                //setgf
                int gf1 = firstTeam.getGf();
                gf1 += gft1;
                firstTeam.setGf(gf1);
                int gf2 = secondTeam.getGf();
                gf2 += gft2;
                secondTeam.setGf(gf2);
                //setplayed
                int played1 = firstTeam.getPlayed();
                int played2 = secondTeam.getPlayed();
                played++;
                played1 += played;
                played2 += played;
                firstTeam.setPlayed(played1);
                secondTeam.setPlayed(played2);
                //setga
                int ga1 = firstTeam.getGa();
                ga1 += gft2;
                firstTeam.setGa(ga1);
                int ga2 = secondTeam.getGa();
                ga2 += gft1;
                secondTeam.setGa(ga2);
                //setgd
                int gd1 = gf1 - ga1;
                firstTeam.setGd(gd1);
                int gd2 = gf2 - ga2;
                secondTeam.setGd(gd2);
                //setpoints
                drawn++;
                drawn2++;
                int points = firstTeam.getPoints();
                points += drawn;
                firstTeam.setPoints(points);
                int points2 = secondTeam.getPoints();
                points2 += drawn2;
                secondTeam.setPoints(points2);
                //setdrawn1
                int dw1 = firstTeam.getDrawn();
                drawn += dw1;
                firstTeam.setDrawn(drawn);
                //setdrawn2
                int dw2 = secondTeam.getDrawn();
                drawn += dw2;
                secondTeam.setDrawn(drawn);
            } else {
                //setgf
                int gf1 = firstTeam.getGf();
                gf1 += gft1;
                firstTeam.setGf(gf1);
                int gf2 = secondTeam.getGf();
                gf2 += gft2;
                secondTeam.setGf(gf2);
                //setplayed
                int played1 = firstTeam.getPlayed();
                int played2 = secondTeam.getPlayed();
                played++;
                played1 += played;
                played2 += played;
                firstTeam.setPlayed(played1);
                secondTeam.setPlayed(played2);
                //setga
                int ga1 = firstTeam.getGa();
                ga1 += gft2;
                firstTeam.setGa(ga1);
                int ga2 = secondTeam.getGa();
                ga2 += gft1;
                secondTeam.setGa(ga2);
                //setgd
                int gd1 = gf1 - ga1;
                firstTeam.setGd(gd1);
                int gd2 = gf2 - ga2;
                secondTeam.setGd(gd2);
                won++;
                lose++;
                //setpoints
                int points = secondTeam.getPoints();
                points += won * 3;
                secondTeam.setPoints(points);
                //setwon
                int win = secondTeam.getWon();
                won += win;
                secondTeam.setWon(won);
                //setlost
                int lost = firstTeam.getLost();
                lose += lost;
                firstTeam.setLost(lose);
            }
            updateByName(firstTeam);
            updateByName(secondTeam);
        }
    }

    public static void updateByName(Team t1) throws SQLException {
        footballRepository.updateData(t1);

    }

    public static void setTeam(String teamName) throws SQLException {
        footballRepository.addClub(teamName);
    }

    public static Team findName(String name1) throws SQLException {
        Team teams;
        try {
            teams = footballRepository.findByName(name1);
            return teams;
        } catch (Exception e) {
            setTeam(name1);
            System.out.println("Team Not Found !");
        }
        return null;
    }
    public class ClubComparator implements Comparator<Team> {

        @Override
        public int compare(Team obj1, Team obj2) {
            return Integer.valueOf(obj1.getPoints()).compareTo(Integer.valueOf(obj2.getPoints()))  ;
        }
    }
}