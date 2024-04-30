package com.cld.usuarios.services;

import com.cld.usuarios.constants.UsuariosConstants;
import com.cld.usuarios.dao.UsuarioDao;
import com.cld.usuarios.mapper.UsuarioMapper;
import com.cld.usuarios.models.GenericResponse;
import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements  IUsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    Util util;
    @Autowired
    private UsuarioDao dao;

    public GenericResponse<List<UsuarioDto>> finallUsers(){
        List<UsuarioDto> listaUsuarios = new ArrayList<>();
        try{
            listaUsuarios = dao.findAll().stream().map(
                    usuario -> UsuarioMapper.mapper.usuarioToUsuarioDto(usuario)
            ).collect(Collectors.toList());
            if(new ArrayList<>().isEmpty()){
                LOGGER.info("=> cld-entity-service-usuarios-v1");
                LOGGER.info("=> GET /usuarios NOT FOUND");
                throw new UsuariosException(util.getFolio(), Collections.singletonList(UsuariosConstants.NOT_FOUND),
                        EExceptionMessage.E404);
            }
        }catch (Exception e){
            LOGGER.info("=> cld-entity-service-usuarios-v1");
            LOGGER.info("=> GET /usuarios ERROR");
            throw new UsuariosException(util.getFolio(), Collections.singletonList(UsuariosConstants.EXCEPCION_DETAILS_500),
                    EExceptionMessage.E500);
        }
        LOGGER.info("=> cld-entity-service-usuarios-v1");
        LOGGER.info("=> GET /usuarios SUCCESS");
        return new GenericResponse<>(UsuariosConstants.SUCCESSFUL_REQUEST_MESSAGE, util.getFolio(),listaUsuarios);
    }

}
