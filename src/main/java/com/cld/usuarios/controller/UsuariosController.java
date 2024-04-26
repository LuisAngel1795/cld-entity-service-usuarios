package com.cld.usuarios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cld.usuarios.constants.UsuariosConstants.BASEPATH;

@RestController
@RequestMapping(BASEPATH)
public class UsuariosController {

    @GetMapping
    public Usuario
}
