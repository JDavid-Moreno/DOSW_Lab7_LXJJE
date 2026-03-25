package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.TeamMemberEntity;
import edu.eci.dosw.TechCup.model.TeamMember;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMemberMapper {

    TeamMemberModel toModel(TeamMemberEntity entity);

    TeamMemberEntity toEntity(TeamMember model);

}