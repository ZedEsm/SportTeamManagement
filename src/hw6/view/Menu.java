package hw6.view;

import hw6.entity.FootballTeam;
import hw6.entity.Team;
import hw6.entity.VolleyballTeam;
import hw6.service.FootballService;
import hw6.service.VolleyballService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String[] options = {"1- Add Club", "2- Delete Club", "3- League"};
    private static final FootballService footballservice = new FootballService();

    private static final VolleyballService volleyballService = new VolleyballService();
    private static FootballTeam firstFootballTeam;

    private static Team firstVolleyballTeam;
    static List<Team> teamList;
    static int gft1;
    static int gft2;

    static int setHomeTeam;

    static int setForeignTeam;

    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("1- Volleyball 2- Football ?");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                printMenu(options);
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter Name Of Club:");
                        VolleyballTeam team = new VolleyballTeam(scanner.next());
                        volleyballService.addTeam(team.getName());
                        break;
                    case 2:
                        System.out.println("Enter Name For Deleting:");
                        String name = scanner.next();
                        volleyballService.deleteTeam(name);
                        break;
                    case 3:
                        String[] s = {"1- Join Game", "2- View Information About Club", "3- View Table Of Composition"};
                        printMenu(s);
                        int choice = Integer.parseInt(scanner.next());
                        switch (choice) {
                            case 1:
                                System.out.println("Enter Name Of Team1: ");
                                String nameTeam = scanner.next();
                                volleyballCompositionStart(nameTeam);
                                break;
                            case 2:
                                System.out.println("Enter Name Of Club:");
                                name = scanner.next();
                                Team volleyballTem = findVolleyballByName(name);
                                if (volleyballTem != null) {
                                    displayDetails(volleyballTem);
                                } else {
                                    System.out.println("These Volleyball_Team Is Not In League");
                                }
                                break;
                            case 3:
                                teamList = volleyballService.viewRankingInfo();
                                displayTableComposition();
                                break;

                        }

                }

                break;
            case 2:
                printMenu(options);
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("Enter Name Of Club:");
                        String clubName = scanner.next();
                        FootballTeam footballTeam = new FootballTeam(clubName);
                        footballservice.addTeam(footballTeam.getName());
                    }
                    case 2 -> {
                        System.out.println("Enter Name For Deleting:");
                        String name = scanner.next();
                        footballservice.deleteTeam(name);
                    }
                    case 3 -> {
                        String[] s = {"1- Join Game", "2- View Information About Club", "3- View Table Of Composition"};
                        printMenu(s);
                        int choice = Integer.parseInt(scanner.next());
                        switch (choice) {
                            case 1 -> {
                                System.out.println("Enter Name Of Team1: ");
                                String nameTeam = scanner.next();
                                footballMatchStart(nameTeam);
                            }
                            case 2 -> {
                                System.out.println("Enter Name Of Club:");
                                String name = scanner.next();
                                FootballTeam footballTeam1 = (FootballTeam) findName(name);
                                if (footballTeam1 != null) {
                                    displayDetails(footballTeam1);
                                } else {
                                    System.out.println("These FootballTeam Is Not In League");
                                }
                            }
                            case 3 -> {
                                teamList = footballservice.viewRankingInfo();
                                displayTableComposition();
                            }
                        }
                    }
                }
                break;
        }
    }

    private static void displayTableComposition() {
        String leftAlignFormat = "| %-15s | %-4d |%n";
        System.out.format("+-----------------+------+%n");
        System.out.format("| Club Name       |Points|%n");
        System.out.format("+-----------------+------+%n");
        for (Team team : teamList) {
            System.out.format(leftAlignFormat, team.getName(), team.getPoints());

        }
        System.out.format("+-----------------+------+%n");
    }

    public static void displayDetails(Team team) {

        if (team instanceof FootballTeam) {
            System.out.println("+------+-------+---------+----------------+--------------+--------+----------------+");
            System.out.println("| Wins | Draws | Lost | Goals Forward | Goals Against | Matched Played |  Points|");
            System.out.println("+------+-------+---------+----------------+--------------+--------+----------------+");
            System.out.println("| " + team.getWon() + "    | " + ((FootballTeam) team).getDrawn() + "     | " + team.getLose() + "  "
                    + "     | " + ((FootballTeam) team).getGf() + "              | " + ((FootballTeam) team).getGa() + ""
                    + "            | " + team.getPlayed() + "      | " + team.getPoints() + "              |");
            System.out.println("+------+-------+---------+----------------+--------------+--------+----------------+");
        } else {
            System.out.println("+------+-------+---------+----------------+");
            System.out.println("| Wins | Lost  | Matched Played |  Points|");
            System.out.println("+------+-------+---------+----------------+");
            System.out.println("| " + team.getWon() + "     | " + team.getLose() + "  "
                    + "     | " + team.getPlayed() + "      | " + team.getPoints());
            System.out.println("+------+-------+---------+----------------+");
        }


    }


    public static void footballMatchStart(String t1) throws Exception {
        firstFootballTeam = (FootballTeam) findName(t1);
        List<Team> teams1 = showFootTable();
        for (Team footballTeam : teams1) {
            if (!footballTeam.getName().equals(firstFootballTeam.getName()) && (footballTeam.getName() != null)) {
                System.out.println(footballTeam.getName());
                System.out.println("Enter Goal Of Team1:");
                gft1 = scanner.nextInt();
                System.out.println("Enter goal Of Team2");
                gft2 = scanner.nextInt();
                if (gft1 > gft2) {

                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = ((FootballTeam) footballTeam).getGf();
                    gf2 += gft2;
                    ((FootballTeam) footballTeam).setGf(gf2);

                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = footballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    footballTeam.setPlayed(played2);

                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = ((FootballTeam) footballTeam).getGa();
                    ga2 += gft1;
                    ((FootballTeam) footballTeam).setGa(ga2);

                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    ((FootballTeam) footballTeam).setGd(gd2);
                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;

                    int points = firstFootballTeam.getPoints();
                    points += won * 3;
                    firstFootballTeam.setPoints(points);

                    int win = firstFootballTeam.getWon();
                    won += win;
                    firstFootballTeam.setWon(won);

                    int lost = footballTeam.getLose();
                    lose += lost;
                    footballTeam.setLose(lose);
                } else if (gft1 == gft2) {

                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = ((FootballTeam) footballTeam).getGf();
                    gf2 += gft2;
                    ((FootballTeam) footballTeam).setGf(gf2);

                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = footballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    footballTeam.setPlayed(played2);

                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = ((FootballTeam) footballTeam).getGa();
                    ga2 += gft1;
                    ((FootballTeam) footballTeam).setGa(ga2);

                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    ((FootballTeam) footballTeam).setGd(gd2);
                    int drawn = 0;
                    int drawn2 = 0;
                    drawn += 1;
                    drawn2 += 1;
                    int points = firstFootballTeam.getPoints();
                    points += 1;
                    firstFootballTeam.setPoints(points);
                    int points2 = footballTeam.getPoints();
                    points2 += 1;
                    footballTeam.setPoints(points2);

                    int dw1 = firstFootballTeam.getDrawn();
                    drawn += dw1;
                    firstFootballTeam.setDrawn(drawn);

                    int dw2 = ((FootballTeam) footballTeam).getDrawn();
                    drawn2 += dw2;
                    ((FootballTeam) footballTeam).setDrawn(drawn2);
                } else {

                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = ((FootballTeam) footballTeam).getGf();
                    gf2 += gft2;
                    ((FootballTeam) footballTeam).setGf(gf2);

                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = footballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    footballTeam.setPlayed(played2);

                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = ((FootballTeam) footballTeam).getGa();
                    ga2 += gft1;
                    ((FootballTeam) footballTeam).setGa(ga2);

                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    ((FootballTeam) footballTeam).setGd(gd2);
                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;

                    int points = footballTeam.getPoints();
                    points += won * 3;
                    footballTeam.setPoints(points);

                    int win = footballTeam.getWon();
                    won += win;
                    footballTeam.setWon(won);

                    int lost = firstFootballTeam.getLose();
                    lose += lost;
                    firstFootballTeam.setLose(lose);
                }
                footballservice.joinGame(firstFootballTeam);
                footballservice.joinGame(footballTeam);
                footballservice.viewClubTable(firstFootballTeam, footballTeam, gft1, gft2);
                System.out.println("Match Finished");
            }
        }


    }

    public static Team findName(String name1) {
        Team teams;
        try {
            teams = footballservice.findBName(name1);
            return teams;
        } catch (Exception e) {
            System.out.println("FootballTeam Not Found !");
        }
        return null;
    }

    public static Team findVolleyballByName(String name1) {
        Team teams;
        try {
            teams = volleyballService.findBName(name1);
            return teams;
        } catch (Exception e) {
            System.out.println("Volleyball_Team Not Found !");
        }
        return null;
    }

    public static void volleyballCompositionStart(String name) throws SQLException {
        firstVolleyballTeam = findVolleyballByName(name);
        List<Team> volleyball = showVolleyballTable();
        for (Team volleyballTeam : volleyball) {
            if (!volleyballTeam.getName().equals(firstVolleyballTeam.getName()) && (volleyballTeam.getName() != null)) {
                System.out.println(volleyballTeam.getName());
                System.out.println("Enter Goal Of Team1:");
                setHomeTeam = scanner.nextInt();
                System.out.println("Enter goal Of Team2");
                setForeignTeam = scanner.nextInt();
                if (setHomeTeam == 3 && setForeignTeam == 0 || setHomeTeam == 3 && setForeignTeam == 1) {

                    int played1 = firstVolleyballTeam.getPlayed();
                    int played2 = volleyballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstVolleyballTeam.setPlayed(played1);
                    volleyballTeam.setPlayed(played2);

                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;

                    int points = firstVolleyballTeam.getPoints();
                    points += 3;
                    firstVolleyballTeam.setPoints(points);

                    int win = firstVolleyballTeam.getWon();
                    won += win;
                    firstVolleyballTeam.setWon(won);

                    int lost = volleyballTeam.getLose();
                    lose += lost;
                    volleyballTeam.setLose(lose);

                } else if (setHomeTeam == 3 && setForeignTeam == 2) {
                    int played1 = firstVolleyballTeam.getPlayed();
                    int played2 = volleyballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstVolleyballTeam.setPlayed(played1);
                    volleyballTeam.setPlayed(played2);

                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;

                    int points = firstVolleyballTeam.getPoints();
                    points += 2;
                    firstVolleyballTeam.setPoints(points);

                    int points2 = volleyballTeam.getPoints();
                    points2 += 1;
                    volleyballTeam.setPoints(points2);

                    int win = firstVolleyballTeam.getWon();
                    won += win;
                    firstVolleyballTeam.setWon(won);

                    int lost = volleyballTeam.getLose();
                    lose += lost;
                    volleyballTeam.setLose(lose);
                } else if (setHomeTeam == 0 && setForeignTeam == 3 || setHomeTeam == 1 && setForeignTeam == 3) {
                    int played1 = firstVolleyballTeam.getPlayed();
                    int played2 = volleyballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstVolleyballTeam.setPlayed(played1);
                    volleyballTeam.setPlayed(played2);

                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;

                    int points = volleyballTeam.getPoints();
                    points += 3;
                    volleyballTeam.setPoints(points);

                    int win = volleyballTeam.getWon();
                    won += win;
                    volleyballTeam.setWon(won);

                    int lost = firstVolleyballTeam.getLose();
                    lose += lost;
                    firstVolleyballTeam.setLose(lose);
                } else if (setHomeTeam == 2 && setForeignTeam == 3) {

                    int played1 = firstVolleyballTeam.getPlayed();
                    int played2 = volleyballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstVolleyballTeam.setPlayed(played1);
                    volleyballTeam.setPlayed(played2);

                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;

                    int points = firstVolleyballTeam.getPoints();
                    points += 1;
                    firstVolleyballTeam.setPoints(points);

                    int points2 = volleyballTeam.getPoints();
                    points2 += 2;
                    volleyballTeam.setPoints(points2);

                    int win = volleyballTeam.getWon();
                    won += win;
                    volleyballTeam.setWon(won);

                    int lost = firstVolleyballTeam.getLose();
                    lose += lost;
                    firstVolleyballTeam.setLose(lose);

                }
                volleyballService.joinGame(firstVolleyballTeam);
                volleyballService.joinGame(volleyballTeam);
                volleyballService.viewClubTable(firstVolleyballTeam, volleyballTeam, setHomeTeam, setForeignTeam);
            }

        }
    }

    public static List<Team> showVolleyballTable() throws SQLException {
        teamList = volleyballService.viewRankingInfo();
        return teamList;
    }

    public static List<Team> showFootTable() throws SQLException {
        teamList = footballservice.viewRankingInfo();
        return teamList;
    }

}