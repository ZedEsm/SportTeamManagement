package hw6.view;

import hw6.entity.Football_Team;
import hw6.repository.FootballRepository;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String[] options = {"1- Add Club", "2- Delete Club", "3- Clubs"};
    private static final FootballRepository footballRepository = new FootballRepository();
    private static int drawn;
    private static int drawn2;
    private static Football_Team firstFootballTeam;
    static List<Football_Team> footballTeams;
    static int gft1;
    static int gft2;

    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
       // System.out.println("Choose your option : ");
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        printMenu(options);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Enter Name Of Club:");
                String clubName = scanner.next();
                Football_Team footballTeam = new Football_Team(clubName);
                setTeam(footballTeam.getClub());
                break;
            case 2:
                List<Football_Team> teams = footballRepository.showTable();
                for (int i = 0; i <teams.size() ; i++) {
                    System.out.println(teams.get(i).getClub());
                }
                System.out.println("Wanna Delete Wich Of Theme?");
                String del = scanner.next();

                break;
            case 3:
                String s[] = {"1- Join Game", "2- View Information About Club", "3- View Table Of Composition"};
                printMenu(s);
                int choise = Integer.parseInt(scanner.next());
                switch (choise) {
                    case 1:
                        System.out.println("Enter Name Of Team1: ");
                        String nameTeam = scanner.next();
                        gameWithTwoTeams(nameTeam);


                        break;
                    case 2:
                        System.out.println("Enter Name Of Club:");
                        String name = scanner.next();
                        Football_Team footballTeam1 = findName(name);
                        if (footballTeam1 != null) {
                            displayDetails(footballTeam1);
                        } else {
                            System.out.println("These Football_Team Is Not In League");
                        }
                        break;
                    case 3:
                        displayTableComposition();
                        break;
                }
                break;

        }

    }

    public static void displayTableComposition() throws SQLException {
        footballTeams = footballRepository.showTable();
        String leftAlignFormat = "| %-15s | %-4d |%n";
        System.out.format("+-----------------+------+%n");
        System.out.format("| Club Name       |Points|%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < footballTeams.size(); i++) {
            System.out.format(leftAlignFormat, footballTeams.get(i).getClub(), footballTeams.get(i).getPoints());

        }
        System.out.format("+-----------------+------+%n");
    }

    public static void displayDetails(Football_Team index) {
        System.out.println("+------+-------+---------+----------------+--------------+--------+----------------+");
        System.out.println("| Wins | Draws | Lost | Goals Forward | Goals Against | Matched Played |  Points|");
        System.out.println("+------+-------+---------+----------------+--------------+--------+----------------+");
        System.out.println("| " + index.getWon() + "    | " + index.getDrawn() + "     | " + index.getLost() + "  "
                + "     | " + index.getGf() + "              | " + index.getGa() + ""
                + "            | " + index.getPlayed() + "      | " + index.getPoints() + "              |");
        System.out.println("+------+-------+---------+----------------+--------------+--------+----------------+");
    }

    public static List<Football_Team> showFootTable() throws SQLException {
        footballTeams = footballRepository.showTable();
        return footballTeams;
    }


    public static void gameWithTwoTeams(String t1) throws Exception {
        firstFootballTeam = findName(t1);
        List<Football_Team> teams1 = showFootTable();
        for (int j = 0; j < teams1.size(); j++) {
            // System.out.println();
            if (!teams1.get(j).getClub().equals(firstFootballTeam.getClub())) {
                Football_Team secondFootballTeam = teams1.get(j);
                System.out.println(secondFootballTeam.getClub());
                System.out.println("Enter Goal Of Team1:");
                gft1 = scanner.nextInt();
                System.out.println("Enter goal Of Team2");
                gft2 = scanner.nextInt();
                if (gft1 > gft2) {
                    //setgf
                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = secondFootballTeam.getGf();
                    gf2 += gft2;
                    secondFootballTeam.setGf(gf2);
                    //setplayed
                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = secondFootballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    secondFootballTeam.setPlayed(played2);
                    //setga
                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = secondFootballTeam.getGa();
                    ga2 += gft1;
                    secondFootballTeam.setGa(ga2);
                    //setgd
                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    secondFootballTeam.setGd(gd2);
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
                    int lost = secondFootballTeam.getLost();
                    lose += lost;
                    secondFootballTeam.setLost(lose);
                } else if (gft1 == gft2) {
                    //setgf
                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = secondFootballTeam.getGf();
                    gf2 += gft2;
                    secondFootballTeam.setGf(gf2);
                    //setplayed
                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = secondFootballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    secondFootballTeam.setPlayed(played2);
                    //setga
                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = secondFootballTeam.getGa();
                    ga2 += gft1;
                    secondFootballTeam.setGa(ga2);
                    //setgd
                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    secondFootballTeam.setGd(gd2);
                    //setpoints
                    drawn++;
                    drawn2++;
                    int points = firstFootballTeam.getPoints();
                    points += drawn;
                    firstFootballTeam.setPoints(points);
                    int points2 = secondFootballTeam.getPoints();
                    points2 += drawn2;
                    secondFootballTeam.setPoints(points2);
                    //setdrawn1
                    int dw1 = firstFootballTeam.getDrawn();
                    drawn += dw1;
                    firstFootballTeam.setDrawn(drawn);
                    //setdrawn2
                    int dw2 = secondFootballTeam.getDrawn();
                    drawn += dw2;
                    secondFootballTeam.setDrawn(drawn);
                } else {
                    //setgf
                    int gf1 = firstFootballTeam.getGf();
                    gf1 += gft1;
                    firstFootballTeam.setGf(gf1);
                    int gf2 = secondFootballTeam.getGf();
                    gf2 += gft2;
                    secondFootballTeam.setGf(gf2);
                    //setplayed
                    int played1 = firstFootballTeam.getPlayed();
                    int played2 = secondFootballTeam.getPlayed();
                    played1 += 1;
                    played2 += 1;
                    firstFootballTeam.setPlayed(played1);
                    secondFootballTeam.setPlayed(played2);
                    //setga
                    int ga1 = firstFootballTeam.getGa();
                    ga1 += gft2;
                    firstFootballTeam.setGa(ga1);
                    int ga2 = secondFootballTeam.getGa();
                    ga2 += gft1;
                    secondFootballTeam.setGa(ga2);
                    //setgd
                    int gd1 = gf1 - ga1;
                    firstFootballTeam.setGd(gd1);
                    int gd2 = gf2 - ga2;
                    secondFootballTeam.setGd(gd2);
                    int won = 0;
                    won += 1;
                    int lose = 0;
                    lose += 1;
                    //setpoints
                    int points = secondFootballTeam.getPoints();
                    points += won * 3;
                    secondFootballTeam.setPoints(points);
                    //setwon
                    int win = secondFootballTeam.getWon();
                    won += win;
                    secondFootballTeam.setWon(won);
                    //setlost
                    int lost = firstFootballTeam.getLost();
                    lose += lost;
                    firstFootballTeam.setLost(lose);
                }
                updateByName(firstFootballTeam);
                updateByName(secondFootballTeam);
                insertToMatchTable(firstFootballTeam, secondFootballTeam);
                System.out.println("Match Finished");
            }
        }


    }

    public static void insertToMatchTable(Football_Team firstFootballTeam, Football_Team secondeFootbalTeam) throws SQLException {
        footballRepository.match(firstFootballTeam, secondeFootbalTeam, gft1, gft2);

    }

    public static void updateByName(Football_Team t1) throws SQLException {
        footballRepository.updateData(t1);

    }

    public static void setTeam(String teamName) throws SQLException {
        footballRepository.addClub(teamName);
    }

    public static Football_Team findName(String name1) throws SQLException {
        Football_Team teams;
        try {
            teams = footballRepository.findByName(name1);
            return teams;
        } catch (Exception e) {
            System.out.println("Football_Team Not Found !");
        }
        return null;
    }

    public class ClubComparator implements Comparator<Football_Team> {
        @Override
        public int compare(Football_Team obj1, Football_Team obj2) {
            return Integer.valueOf(obj1.getPoints()).compareTo(Integer.valueOf(obj2.getPoints()));
        }
    }
}