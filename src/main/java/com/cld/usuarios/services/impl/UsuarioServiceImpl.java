package com.cld.usuarios.services.impl;

import com.cld.usuarios.constants.UsuariosConstants;
import com.cld.usuarios.dao.UsuarioDao;
import com.cld.usuarios.enums.EExceptionMessage;
import com.cld.usuarios.exceptions.UsuariosException;
import com.cld.usuarios.mapper.UsuarioMapper;
import com.cld.usuarios.models.UsuarioGetResponse;
import com.cld.usuarios.models.UsuariosGetResponse;
import com.cld.usuarios.models.UsuariosPostResponse;
import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.models.entity.Usuario;
import com.cld.usuarios.services.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public UsuariosGetResponse findAllUsers() {
        List<UsuarioDto> listaUsuarios = new ArrayList<>();
        try {
            listaUsuarios = dao.findByEstatusNot("EliminadoLogico").stream().map(
                    UsuarioMapper.mapper::usuarioToUsuarioDto
            ).collect(Collectors.toList());

        } catch (Exception e) {
            LOGGER.info("=> ERROR");
            throw new UsuariosException(Collections.singletonList(UsuariosConstants.EXCEPCION_DETAILS_500),
                    EExceptionMessage.E500);
        }
        LOGGER.info("=> SUCCESS");
        return new UsuariosGetResponse(listaUsuarios);
    }

    @Override
    public UsuarioGetResponse findUserById(String id) {
        Usuario user = validateUser(Long.parseLong(id));
        if(!(user != null)){
            LOGGER.info("=> NOTFOUND");
            throw new UsuariosException(Collections.singletonList(UsuariosConstants.NOT_FOUND),
                    EExceptionMessage.E404);
        }
        return new UsuarioGetResponse(UsuarioMapper.mapper.usuarioToUsuarioDto(user));
    }



    @Override
    public UsuariosPostResponse createOne(UsuarioDto usuario) {
        Usuario UserEntity = UsuarioMapper.mapper.usuarioDtoToUsuario(usuario);
        Usuario createdUser;
        try {
            createdUser = dao.save(UserEntity);
        } catch (Exception e) {
            LOGGER.info("=> ERROR");
            throw new UsuariosException(Collections.singletonList(UsuariosConstants.EXCEPCION_DETAILS_500),
                    EExceptionMessage.E500);
        }
        return new UsuariosPostResponse(String.valueOf(createdUser.getId()));
    }
    @Override

    /**RECIBO EL OBJETO USUARIO DTO SIN ID, YA QUE EL ID
     * VIENE COMO PATHPARAM. SI ESE ID SE ENCUENTRA EN LA BASE DE DATOS AHORA SI SETEO EL ID EN EL OBJETO
     * Y JPA HARA LA ACTUALIZACIÓN YA QUE ESE ID SI EXISTE*/
    public void updateOne(UsuarioDto usuario, String id) {
        if (!(validateUser(Long.parseLong(id))!=null)) {
            LOGGER.info("=> NOTFOUND");
            throw new UsuariosException(Collections.singletonList(UsuariosConstants.NOT_FOUND),
                    EExceptionMessage.E404);
        }
        usuario.setId(Long.parseLong(id));
        Usuario UserEntity = UsuarioMapper.mapper.usuarioDtoToUsuario(usuario);
        dao.save(UserEntity);
    }
    @Override
    public void deleteOne(String id) {
        Usuario user = validateUser(Long.parseLong(id));
        if (!(user!=null)) {
            LOGGER.info("=> NOTFOUND");
            throw new UsuariosException(Collections.singletonList(UsuariosConstants.NOT_FOUND),
                    EExceptionMessage.E404);
        }
        user.setEstatus(UsuariosConstants.LOGIC_DELETE);
        dao.save(user);
    }






    private Usuario validateUser(Long idUser) {
        Usuario user;
        try {
            user = dao.findById(idUser).orElse(null);
        } catch (Exception e) {
            LOGGER.info("=> ERROR");
            throw new UsuariosException(Collections.singletonList(UsuariosConstants.EXCEPCION_DETAILS_500),
                    EExceptionMessage.E500);
        }
        return (user);
    }
}
