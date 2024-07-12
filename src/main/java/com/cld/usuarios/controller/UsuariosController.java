package com.cld.usuarios.controller;


import com.cld.usuarios.models.UsuarioGetResponse;
import com.cld.usuarios.models.UsuariosPostResponse;
import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.cld.usuarios.models.UsuariosGetResponse;


import javax.validation.Valid;
import javax.websocket.server.PathParam;

import static com.cld.usuarios.constants.UsuariosConstants.BASEPATH;

@RestController
@RequestMapping(BASEPATH)
@CrossOrigin(origins = "http://localhost:3000")
public class UsuariosController {

    @Autowired
    private IUsuarioService service;

    @GetMapping
    public UsuariosGetResponse getUsuarios(){
        return service.findAllUsers();
    }

    @GetMapping("/{id}")
    public UsuarioGetResponse getUsuario(@PathVariable String id){
        return service.findUserById(id);
    }

    @PutMapping("/{id}")
    public void putUsuarios(@RequestBody UsuarioDto usuario,
                                           @PathVariable String id){
        service.updateOne(usuario, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuariosPostResponse postUsuarios(@RequestBody UsuarioDto usuario){
        return service.createOne(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable String id){
        service.deleteOne(id);
    }

}
