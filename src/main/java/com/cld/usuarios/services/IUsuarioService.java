package com.cld.usuarios.services;

import com.cld.usuarios.models.GenericResponse;
import com.cld.usuarios.models.dto.UsuarioDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {
    public GenericResponse<List<UsuarioDto>> finallUsers();
}
