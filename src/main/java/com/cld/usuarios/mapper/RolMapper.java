package com.cld.usuarios.mapper;

import com.cld.usuarios.models.dto.RolDto;
import com.cld.usuarios.models.entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mapper
public interface RolMapper {
    @Mapping(source = "descripcion", target = "descripciondelRol")
    @Named("toDto")
    RolDto toDto(Rol rol);


    List<RolDto> todoList(List<Rol> roles);

}