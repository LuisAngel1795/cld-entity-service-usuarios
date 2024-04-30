package com.cld.usuarios.mapper;

import com.cld.usuarios.models.dto.RolDto;
import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.models.entity.Rol;
import com.cld.usuarios.models.entity.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = RolMapper.class)
public interface UsuarioMapper {
    UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDto usuarioToUsuarioDto(Usuario usuario);


    @Mapping(target = "contrasena", ignore = true)
    Usuario usuarioDtoToUsuario(UsuarioDto usuario);
}
