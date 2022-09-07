package hw6.service;

import hw6.entity.Team;

import java.sql.SQLException;
import java.util.List;

public interface LeagueService {
    void addTeam(String name) throws SQLException;
    void deleteTeam(String name) throws SQLException;
    void joinGame(Team team) throws SQLException;

    void viewClubTable(Team team, Team team1,int gf1,int gf2) throws SQLException;
    List<Team> viewRankingInfo() throws SQLException;
   Team findBName(String name) throws Exception;
}
