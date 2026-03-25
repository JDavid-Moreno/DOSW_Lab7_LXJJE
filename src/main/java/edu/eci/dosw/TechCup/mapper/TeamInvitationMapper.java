package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.TeamInvitationEntity;
import edu.eci.dosw.TechCup.model.TeamInvitation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamInvitationMapper {

    TeamInvitation toModel(TeamInvitationEntity entity);

    TeamInvitationEntity toEntity(TeamInvitation model);

}