package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.RoleEntity;
import org.mapstruct.Mapper;
import edu.eci.dosw.TechCup.model.Role;

@Mapper(componentModel = "spring",
       uses = {PermissionMapper.class})
public interface RoleMapper {

    Role toModel(RoleEntity entity);

    RoleEntity toEntity(Role model);
}
