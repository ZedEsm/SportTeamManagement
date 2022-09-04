package hw6.repository;

import hw6.entity.Team;
import hw6.util.ApplicationConstant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FootballRepository {
    public boolean addClub(String teamName) throws SQLException {
        String QUERY = "INSERT INTO footbal(club,played,won,drawn,lost,gf,ga,gd,points) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepareStatement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        prepareStatement.setString(1, teamName);
        prepareStatement.setInt(2, 0);
        prepareStatement.setInt(3, 0);
        prepareStatement.setInt(4, 0);
        prepareStatement.setInt(5, 0);
        prepareStatement.setInt(6, 0);
        prepareStatement.setInt(7, 0);
        prepareStatement.setInt(8, 0);
        prepareStatement.setInt(9, 0);
        int index = prepareStatement.executeUpdate();
        if (index > 0) {
            System.out.println("Club Added");
            return true;
        } else {
            System.out.println("Club Addition Failed");
            return false;
        }
    }
    public Team findByName(String name) throws Exception {
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
            Team team = new Team(clubName,played,won,drawn,lost,gf,ga,gd,points);
            return team;
        }
        throw new Exception("Team Not Found");
    }
    public void updateData(Team team) throws SQLException {
        String QUERY = "UPDATE footbal SET played=?,won=?,drawn =?,lost = ?,gf = ?,ga =?,gd = ?,points =? WHERE club = ?";
        PreparedStatement prepareStatement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        prepareStatement.setInt(1,team.getPlayed());
        prepareStatement.setInt(2,team.getWon());
        prepareStatement.setInt(3,team.getDrawn());
        prepareStatement.setInt(4,team.getLost());
        prepareStatement.setInt(5,team.getGf());
        prepareStatement.setInt(6,team.getGa());
        prepareStatement.setInt(7,team.getGd());
        prepareStatement.setInt(8,team.getPoints());
        prepareStatement.setString(9,team.getClub());
        int upIndex = prepareStatement.executeUpdate();
        if(upIndex<0) {
            System.out.println("Update Failed");
        }
//        Team teamUpdated =
//        return
    }

}
