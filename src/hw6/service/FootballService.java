package hw6.service;

import hw6.entity.FootballTeam;
import hw6.entity.Team;
import hw6.repository.FootballRepository;

import java.sql.SQLException;
import java.util.List;

public class FootballService implements LeagueService {
    private static final FootballRepository footballRepository = new FootballRepository();

    @Override
    public void addTeam(String teamName) throws SQLException {
        footballRepository.addClub(teamName);

    }

    @Override
    public void deleteTeam(String name) {

    }

    @Override
    public void joinGame(Team footballTeam) throws SQLException {
        footballRepository.updateData((FootballTeam) footballTeam);

    }

    @Override
    public void viewClubTable(Team firstFootballTeam, Team secondFootballTeam, int gft1, int gft2) throws SQLException {
        footballRepository.match((FootballTeam) firstFootballTeam, (FootballTeam) secondFootballTeam, gft1, gft2);
    }


    @Override
    public List<Team> viewRankingInfo() throws SQLException {
        return footballRepository.showTable();
    }

    @Override
    public Team findBName(String name) throws Exception {
        return footballRepository.findByName(name);

    }
}
