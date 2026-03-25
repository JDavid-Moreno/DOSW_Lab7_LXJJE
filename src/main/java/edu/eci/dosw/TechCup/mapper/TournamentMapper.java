package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.TournamentEntity;
import edu.eci.dosw.TechCup.model.Tournament;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    Tournament toModel(TournamentEntity entity);

    TournamentEntity toEntity(Tournament model);

}