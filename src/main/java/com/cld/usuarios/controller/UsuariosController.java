package com.cld.usuarios.controller;

import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.models.entity.Usuario;
import com.cld.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cld.usuarios.constants.UsuariosConstants.BASEPATH;

@RestController
@RequestMapping(BASEPATH)
public class UsuariosController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioDto> getUsuarios(){
        return service.finallUsers();
    }
}
