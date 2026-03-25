package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.UserEntity;
import edu.eci.dosw.TechCup.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {FileMapper.class}
)

public interface UserMapper {

    User toModel(UserEntity entity);

    UserEntity toEntity(User model);

}