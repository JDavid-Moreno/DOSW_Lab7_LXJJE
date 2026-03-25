package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.TeamEntity;
import edu.eci.dosw.TechCup.model.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamModel toModel(TeamEntity entity);

    TeamEntity toEntity(Team model);

}