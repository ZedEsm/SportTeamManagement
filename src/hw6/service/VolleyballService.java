package hw6.service;

import hw6.entity.Football_Team;
import hw6.entity.Team;
import hw6.entity.VolleyballTeam;
import hw6.repository.VolleyballRepository;

import java.sql.SQLException;
import java.util.List;

public class VolleyballService implements LeagueService {
    private static final VolleyballRepository VOLLEYBALL_REPOSITORY = new VolleyballRepository();

    @Override
    public void addTeam(String name) throws SQLException {
        VOLLEYBALL_REPOSITORY.addVolleyballClub(name);
    }

    @Override
    public void deleteTeam(String name) throws SQLException {
        VOLLEYBALL_REPOSITORY.delete(name);

    }

    @Override
    public void joinGame(Team team) throws SQLException {
        VOLLEYBALL_REPOSITORY.updateData((VolleyballTeam) team);

    }

    @Override
    public void viewClubTable(Team team, Team team1, int seth, int setf) throws SQLException {
        VOLLEYBALL_REPOSITORY.match((VolleyballTeam) team, (VolleyballTeam) team1, seth, setf);
    }

    @Override
    public List<Team> viewRankingInfo() throws SQLException {
        return VOLLEYBALL_REPOSITORY.showVolleyballTable();
    }

    @Override
    public Team findBName(String name) throws Exception {
        return VOLLEYBALL_REPOSITORY.findByName(name);
    }
}
