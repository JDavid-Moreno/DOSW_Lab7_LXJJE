package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.PermissionEntity;
import org.mapstruct.Mapper;
import edu.eci.dosw.TechCup.model.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toModel(PermissionEntity entity);

    PermissionEntity toEntity(Permission model);
}
