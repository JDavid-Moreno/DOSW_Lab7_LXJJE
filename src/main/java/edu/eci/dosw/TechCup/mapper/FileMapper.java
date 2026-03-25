package edu.eci.dosw.TechCup.mapper;

import edu.eci.dosw.TechCup.entity.FileEntity;
import edu.eci.dosw.TechCup.model.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {

    File toModel(FileEntity entity);

    FileEntity toEntity(File model);

}