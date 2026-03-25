package edu.eci.dosw.TechCup.service;

import edu.eci.dosw.TechCup.entity.TeamEntity;
import edu.eci.dosw.TechCup.mapper.TeamMapper;
import edu.eci.dosw.TechCup.model.Team;
import edu.eci.dosw.TechCup.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.eci.dosw.TechCup.exception.TeamException;

@Service
public class TeamServiceProd implements TeamService{
    private TeamRepository teamRepository;
    private TeamMapper teamMapper;

    public TeamServiceProd(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Transactional
    public Team findById(Long id) {
        TeamEntity entity = teamRepository.findById(id)
            .orElseThrow(() -> new TeamException("Team not Exist"));
        return teamMapper.toModel(entity);
    }

    @Transactional
    public Team findByName(String name) {
        return teamRepository.findAllByName(name)
        .stream().findFirst().map(teamMapper::toModel).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    @Transactional
    public Team createTeam(Team team) {
        TeamEntity entity = teamMapper.toEntity(team);
        return teamMapper.toModel(teamRepository.save(entity));
    }

    @Transactional
    public Team updateTeam(Long id, Team team) {
        TeamEntity teamExists = teamRepository.findById(id)
            .orElseThrow(() -> new TeamException("Team not found"));
        teamExists.setId(team.getId());

        return teamMapper.toModel(teamRepository.save(teamExists));
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

}
