package com.cld.usuarios.controller;


import com.cld.usuarios.models.UsuariosPostResponse;
import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.cld.usuarios.models.UsuariosGetResponse;


import javax.validation.Valid;

import static com.cld.usuarios.constants.UsuariosConstants.BASEPATH;

@RestController
@RequestMapping(BASEPATH)
public class UsuariosController {

    @Autowired
    private IUsuarioService service;

    @GetMapping
    public UsuariosGetResponse getUsuarios(){
        return service.findAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuariosPostResponse postUsuarios(@RequestBody UsuarioDto usuario){
        return service.createOne(usuario);
    }
}
