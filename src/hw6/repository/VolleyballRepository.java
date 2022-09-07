package hw6.repository;

import hw6.entity.Football_Team;
import hw6.entity.Team;
import hw6.entity.VolleyballTeam;
import hw6.util.ApplicationConstant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolleyballRepository {
    public void addVolleyballClub(String teamName) throws SQLException {
        String query = "INSERT INTO volleyball (teamname,played,won,lose,points) VALUES (?,?,?,?,?)";
        PreparedStatement prepareStatement = ApplicationConstant.getConnection().prepareStatement(query);
        prepareStatement.setString(1, teamName);
        prepareStatement.setInt(2, 0);
        prepareStatement.setInt(3, 0);
        prepareStatement.setInt(4, 0);
        prepareStatement.setInt(5, 0);
        prepareStatement.executeUpdate();
    }
    public void delete(String name) throws SQLException {
        String QUERY = "DELETE FROM volleyball WHERE teamname = ? ";
        PreparedStatement statement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        statement.setString(1, name);
        statement.executeUpdate();

    }
    public VolleyballTeam findByName(String name) throws Exception {
        String QUERY = "SELECT * FROM volleyball WHERE teamname =?";
        PreparedStatement statement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String clubName = resultSet.getString("teamname");
            int played = resultSet.getInt("played");
            int won = resultSet.getInt("won");
            int lost = resultSet.getInt("lose");
            int points = resultSet.getInt("points");
            return new VolleyballTeam(clubName,won,lost,points,played);
        }
        throw new Exception("Voleyball_Team Not Found");
    }
    public List<Team> showVolleyballTable() throws SQLException {
        List<Team> volleyballTeams = new ArrayList<>();
        String query = "SELECT * FROM volleyball ORDER BY points DESC";
        PreparedStatement preparedStatement = ApplicationConstant.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String club = resultSet.getString("teamname");
            int played = resultSet.getInt("played");
            int won = resultSet.getInt("won");
            int lost = resultSet.getInt("lose");
            int points = resultSet.getInt("points");
            Team volleyballTeam = new VolleyballTeam(club,won,lost,points,played);
            volleyballTeams.add(volleyballTeam);

        }
        return volleyballTeams;
    }
    public void updateData(VolleyballTeam volleyballTeam) throws SQLException {
        String QUERY = "UPDATE volleyball SET played=?,won =?,lose = ?,points = ? WHERE teamname=?";
        PreparedStatement prepareStatement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        prepareStatement.setInt(1, volleyballTeam.getPlayed());
        prepareStatement.setInt(2, volleyballTeam.getWon());
        prepareStatement.setInt(3, volleyballTeam.getLose());
        prepareStatement.setInt(4,volleyballTeam.getPoints());
        prepareStatement.setString(5, volleyballTeam.getName());
        int upIndex = prepareStatement.executeUpdate();
        if (upIndex < 0) {
            System.out.println("Update Failed");
        }
    }
    public void match(VolleyballTeam team1, VolleyballTeam team2, int seth, int setf) throws SQLException {
        String QUERY = "INSERT INTO volleymatch(hometeam,awayteam,seth,setf) VALUES (?,?,?,?)";
        PreparedStatement prepareStatement = ApplicationConstant.getConnection().prepareStatement(QUERY);
        prepareStatement.setString(1,team1.getName() );
        prepareStatement.setString(2, team2.getName());
        prepareStatement.setInt(3, seth);
        prepareStatement.setInt(4, setf);
        prepareStatement.executeUpdate();
    }
}
