package com.cld.usuarios.mapper;

import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.models.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = com.cld.usuarios.mapper.RolMapper.class)
public interface UsuarioMapper {
    UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "idPersona", target = "idPersona")
    @Mapping(source = "fechaRegistro", target = "fechaRegistro")
    @Mapping(source = "estatus", target = "estatus")
    @Mapping(source = "correoElectronico", target = "correoElectronico")
    @Mapping(source = "roles", target = "roles")
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "idPersona", target = "idPersona")
    @Mapping(source = "fechaRegistro", target = "fechaRegistro")
    @Mapping(source = "estatus", target = "estatus")
    @Mapping(source = "correoElectronico", target = "correoElectronico")
    @Mapping(source = "roles", target = "roles")
    @Mapping(target = "contrasena", ignore = true)
    Usuario usuarioDtoToUsuario(UsuarioDto usuario);
}
