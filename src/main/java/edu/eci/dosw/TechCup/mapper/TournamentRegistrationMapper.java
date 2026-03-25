package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.TournamentRegistrationEntity;
import edu.eci.dosw.TechCup.model.TournamentRegistration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TournamentRegistrationMapper {

    TournamentRegistrationModel toModel(TournamentRegistrationEntity entity);

    TournamentRegistrationEntity toEntity(TournamentRegistration model);

}