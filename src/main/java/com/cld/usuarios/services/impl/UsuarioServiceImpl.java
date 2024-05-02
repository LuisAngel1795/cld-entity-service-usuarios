package com.cld.usuarios.services.impl;

import com.cld.usuarios.constants.UsuariosConstants;
import com.cld.usuarios.dao.UsuarioDao;
import com.cld.usuarios.enums.EExceptionMessage;
import com.cld.usuarios.exceptions.UsuariosException;
import com.cld.usuarios.mapper.UsuarioMapper;
import com.cld.usuarios.models.UsuariosGetResponse;
import com.cld.usuarios.models.UsuariosPostResponse;
import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.models.entity.Usuario;
import com.cld.usuarios.services.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioDao dao;

    public UsuariosGetResponse findAllUsers() {
        List<UsuarioDto> listaUsuarios = new ArrayList<>();
        try {
            listaUsuarios = dao.findAll().stream().map(
                    usuario -> UsuarioMapper.mapper.usuarioToUsuarioDto(usuario)
            ).collect(Collectors.toList());

        } catch (Exception e) {
            LOGGER.info("=> ERROR");
            throw new UsuariosException(Collections.singletonList(UsuariosConstants.EXCEPCION_DETAILS_500),
                    EExceptionMessage.E500);
        }
        LOGGER.info("=> SUCCESS");
        return new UsuariosGetResponse(listaUsuarios);
    }


    public UsuariosPostResponse createOne(UsuarioDto usuario){
        Usuario UserEntity = UsuarioMapper.mapper.usuarioDtoToUsuario(usuario);
        Usuario createdUser = dao.save(UserEntity);
        return new UsuariosPostResponse(String.valueOf(createdUser.getId()));
    }

}
