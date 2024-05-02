package com.cld.usuarios.services;

import com.cld.usuarios.models.GenericResponse;
import com.cld.usuarios.models.UsuariosGetResponse;
import com.cld.usuarios.models.UsuariosPostResponse;
import com.cld.usuarios.models.dto.UsuarioDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {
    public UsuariosGetResponse findAllUsers();
    public UsuariosPostResponse createOne(UsuarioDto usuario);
}
