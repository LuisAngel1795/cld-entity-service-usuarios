package com.cld.usuarios.services;

import com.cld.usuarios.dao.UsuarioDao;
import com.cld.usuarios.mapper.UsuarioMapper;
import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao dao;

    public List<UsuarioDto> finallUsers(){
        return dao.findAll().stream().map(
                usuario -> UsuarioMapper.mapper.usuarioToUsuarioDto(usuario)
        ).collect(Collectors.toList());
    }

}
