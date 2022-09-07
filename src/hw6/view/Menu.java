package hw6.view;

import hw6.entity.Football_Team;
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

    private static VolleyballService volleyballService = new VolleyballService();
    private static int drawn;
    private static int drawn2;
    private static Football_Team firstFootballTeam;

    private static Team volleyballT1;
    static List<Team> teamList;
    static int gft1;
    static int gft2;

    static int seth;

    static int setf;

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
                                manageVolleyballGame(nameTeam);
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
                        Football_Team footballTeam = new Football_Team(clubName);
                        footballservice.addTeam(footballTeam.getName());
                    }
                    case 2 -> {
                        //delete
                    }
                    case 3 -> {
                        String[] s = {"1- Join Game", "2- View Information About Club", "3- View Table Of Composition"};
                        printMenu(s);
                        int choice = Integer.parseInt(scanner.next());
                        switch (choice) {
                            case 1 -> {
                                System.out.println("Enter Name Of Team1: ");
                                String nameTeam = scanner.next();
                                gameWithTwoTeams(nameTeam);
                            }
                            case 2 -> {
                                System.out.println("Enter Name Of Club:");
                                String name = scanner.next();
                                Football_Team footballTeam1 = (Football_Team) findName(name);
                                if (footballTeam1 != null) {
                                    displayDetails(footballTeam1);
                                } else {
                                    System.out.println("These Football_Team Is Not In League");
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

    public static void displayTableComposition() throws SQLException {
        showToUserCompatitionList();

    }

    private static void showToUserCompatitionList() {
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

        if (team instanceof Football_Team) {
            System.out.println("+------+-------+---------+----------------+--------------+--------+----------------+");
            System.out.println("| Wins | Draws | Lost | Goals Forward | Goals Against | Matched Played |  Points|");
            System.out.println("+------+-------+---------+----------------+--------------+--------+----------------+");
            System.out.println("| " + team.getWon() + "    | " + ((Football_Team) team).getDrawn() + "     | " + team.getLose() + "  "
                    + "     | " + ((Football_Team) team).getGf() + "              | " + ((Football_Team) team).getGa() + ""
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

    public static List<Team> showFootTable() throws SQLException {
        teamList = footballservice.viewRankingInfo();
        return teamList;
    }


    public static void gameWithTwoTeams(String t1) throws Exception {
        firstFootballTeam = (Football_Team) findName(t1);
        List<Team> teams1 = showFootTable();
        for (Team footballTeam : teams1) {
            if (!footballTeam.getName().equals(firstFootballTeam.getName())) {
                System.out.println(footballTeam.getName());
                System.out.println("Enter Goal Of Team1:");
                gft1 = scanner.nextInt();
                System.out.println("Enter goal Of Team2");
                gft2 = scanner.nextInt();
                if (gft1 > gft2) {
                    //setgf
                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = ((Football_Team) footballTeam).getGf();
                    gf2 += gft2;
                    ((Football_Team) footballTeam).setGf(gf2);
                    //setplayed
                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = footballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    footballTeam.setPlayed(played2);
                    //setga
                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = ((Football_Team) footballTeam).getGa();
                    ga2 += gft1;
                    ((Football_Team) footballTeam).setGa(ga2);
                    //setgd
                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    ((Football_Team) footballTeam).setGd(gd2);
                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;
                    //setpoints
                    int points = firstFootballTeam.getPoints();
                    points += won * 3;
                    firstFootballTeam.setPoints(points);
                    //setwon
                    int win = firstFootballTeam.getWon();
                    won += win;
                    firstFootballTeam.setWon(won);
                    //setlost
                    int lost = footballTeam.getLose();
                    lose += lost;
                    footballTeam.setLose(lose);
                } else if (gft1 == gft2) {
                    //setgf
                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = ((Football_Team) footballTeam).getGf();
                    gf2 += gft2;
                    ((Football_Team) footballTeam).setGf(gf2);
                    //setplayed
                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = footballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    footballTeam.setPlayed(played2);
                    //setga
                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = ((Football_Team) footballTeam).getGa();
                    ga2 += gft1;
                    ((Football_Team) footballTeam).setGa(ga2);
                    //setgd
                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    ((Football_Team) footballTeam).setGd(gd2);
                    //setpoints
                    drawn++;
                    drawn2++;
                    int points = firstFootballTeam.getPoints();
                    points += drawn;
                    firstFootballTeam.setPoints(points);
                    int points2 = footballTeam.getPoints();
                    points2 += drawn2;
                    footballTeam.setPoints(points2);
                    //setdrawn1
                    int dw1 = firstFootballTeam.getDrawn();
                    drawn += dw1;
                    firstFootballTeam.setDrawn(drawn);
                    //setdrawn2
                    int dw2 = ((Football_Team) footballTeam).getDrawn();
                    drawn += dw2;
                    ((Football_Team) footballTeam).setDrawn(drawn);
                } else {
                    //setgf
                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = ((Football_Team) footballTeam).getGf();
                    gf2 += gft2;
                    ((Football_Team) footballTeam).setGf(gf2);
                    //setplayed
                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = footballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    footballTeam.setPlayed(played2);
                    //setga
                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = ((Football_Team) footballTeam).getGa();
                    ga2 += gft1;
                    ((Football_Team) footballTeam).setGa(ga2);
                    //setgd
                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    ((Football_Team) footballTeam).setGd(gd2);
                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;
                    //setpoints
                    int points = footballTeam.getPoints();
                    points += won * 3;
                    footballTeam.setPoints(points);
                    //setwon
                    int win = footballTeam.getWon();
                    won += win;
                    footballTeam.setWon(won);
                    //setlost
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
            System.out.println("Football_Team Not Found !");
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

    public static void manageVolleyballGame(String name) throws SQLException {
        volleyballT1 = findVolleyballByName(name);
        List<Team> volleyball = showVolleyballTable();
        for (Team vballTeam : volleyball) {
            if (!vballTeam.getName().equals(volleyballT1.getName())) {
                System.out.println(vballTeam.getName());
                System.out.println("Enter Goal Of Team1:");
                seth = scanner.nextInt();
                System.out.println("Enter goal Of Team2");
                setf = scanner.nextInt();
                if (seth == 3 && setf == 0 || seth == 3 && setf == 1) {//foregin benivis
                    //setplayed
                    int played1 = volleyballT1.getPlayed();
                    int played2 = vballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    volleyballT1.setPlayed(played1);
                    vballTeam.setPlayed(played2);

                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;
                    //setpoints
                    int points = volleyballT1.getPoints();
                    points += 3;
                    volleyballT1.setPoints(points);
                    //setwon
                    int win = volleyballT1.getWon();
                    won += win;
                    volleyballT1.setWon(won);
                    //setlost
                    int lost = vballTeam.getLose();
                    lose += lost;
                    vballTeam.setLose(lose);

                } else if (seth == 3 && setf == 2) {
                    int played1 = volleyballT1.getPlayed();
                    int played2 = vballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    volleyballT1.setPlayed(played1);
                    vballTeam.setPlayed(played2);

                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;
                    //setpoints
                    int points = volleyballT1.getPoints();
                    points += 2;
                    volleyballT1.setPoints(points);

                    int points2 = vballTeam.getPoints();
                    points2 += 1;
                    vballTeam.setPoints(points2);
                    //setwon
                    int win = volleyballT1.getWon();
                    won += win;
                    volleyballT1.setWon(won);
                    //setlost
                    int lost = vballTeam.getLose();
                    lose += lost;
                    vballTeam.setLose(lose);
                } else if (seth == 0 && setf == 3 || seth == 1 && setf == 3) {

                } else if (seth == 2 && setf == 3) {

                }
                volleyballService.joinGame(volleyballT1);
                volleyballService.joinGame(vballTeam);
                volleyballService.viewClubTable(volleyballT1, vballTeam, seth, setf);
            }

        }
    }

    public static List<Team> showVolleyballTable() throws SQLException {
        teamList = volleyballService.viewRankingInfo();
        return teamList;
    }

}