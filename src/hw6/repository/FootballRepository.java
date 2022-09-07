package hw6.repository;

import hw6.entity.Football_Team;
import hw6.entity.Team;
import hw6.util.ApplicationConstant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FootballRepository {
    public void addClub(String teamName) throws SQLException {
        String query = "INSERT INTO footbal(club,played,won,drawn,lost,gf,ga,gd,points) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepareStatement = ApplicationConstant.getConnection().prepareStatement(query);
        prepareStatement.setString(1, teamName);
        prepareStatement.setInt(2, 0);
        prepareStatement.setInt(3, 0);
        prepareStatement.setInt(4, 0);
        prepareStatement.setInt(5, 0);
        prepareStatement.setInt(6, 0);
        prepareStatement.setInt(7, 0);
        prepareStatement.setInt(8, 0);
        prepareStatement.setInt(9, 0);
        prepareStatement.executeUpdate();
    }

    public Football_Team findByName(String name) throws Exception {
        String QUERY = "SELECT * FROM footbal WHERE club =?";
        PreparedStatement statement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String clubName = resultSet.getString("club");
            int played = resultSet.getInt("played");
            int won = resultSet.getInt("won");
            int drawn = resultSet.getInt("drawn");
            int lost = resultSet.getInt("lost");
            int gf = resultSet.getInt("gf");
            int ga = resultSet.getInt("ga");
            int gd = resultSet.getInt("gd");
            int points = resultSet.getInt("points");
            return new Football_Team(clubName, played, won, drawn, lost, gf, ga, gd, points);
        }
        throw new Exception("Football_Team Not Found");
    }

    public void updateData(Football_Team footballTeam) throws SQLException {
        String QUERY = "UPDATE footbal SET played=?,won=?,drawn =?,lost = ?,gf = ?,ga =?,gd = ?,points =? WHERE club = ?";
        PreparedStatement prepareStatement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        prepareStatement.setInt(1, footballTeam.getPlayed());
        prepareStatement.setInt(2, footballTeam.getWon());
        prepareStatement.setInt(3, footballTeam.getDrawn());
        prepareStatement.setInt(4, footballTeam.getLose());
        prepareStatement.setInt(5, footballTeam.getGf());
        prepareStatement.setInt(6, footballTeam.getGa());
        prepareStatement.setInt(7, footballTeam.getGd());
        prepareStatement.setInt(8, footballTeam.getPoints());
        prepareStatement.setString(9, footballTeam.getName());
        int upIndex = prepareStatement.executeUpdate();
        if (upIndex < 0) {
            System.out.println("Update Failed");
        }
    }

    public List<Team> showTable() throws SQLException {
        List<Team> footballTeams = new ArrayList<>();
        String query = "SELECT * FROM footbal ORDER BY points DESC";
        PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String club = resultSet.getString("club");
            int played = resultSet.getInt("played");
            int won = resultSet.getInt("won");
            int drawn = resultSet.getInt("drawn");
            int lost = resultSet.getInt("lost");
            int gf = resultSet.getInt("gf");
            int ga = resultSet.getInt("ga");
            int gd = resultSet.getInt("gd");
            int points = resultSet.getInt("points");
            Team footballTeam = new Football_Team(club, played, won, drawn, lost, gf, ga, gd, points);
            footballTeams.add(footballTeam);

        }
        return footballTeams;
    }
    public void match(Football_Team footballTeam, Football_Team secondFootballTeam, int gft1, int gft2) throws SQLException {
        String QUERY = "INSERT INTO bazi(hometeam,foreignnteam,goalhome,goalforeign) VALUES (?,?,?,?)";
        PreparedStatement prepareStatement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        prepareStatement.setString(1, footballTeam.getName());
        prepareStatement.setString(2, secondFootballTeam.getName());
        prepareStatement.setInt(3, gft1);
        prepareStatement.setInt(4, gft2);
        prepareStatement.executeUpdate();
    }

}
