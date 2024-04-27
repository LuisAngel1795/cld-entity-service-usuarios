package com.cld.usuarios.mapper;

import com.cld.usuarios.models.dto.RolDto;
import com.cld.usuarios.models.entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolMapper {

    RolMapper mapper = Mappers.getMapper(RolMapper.class);
    @Mapping(source = "descripcion", target = "descripciondelRol")
    RolDto toDto(Rol rol);
}
