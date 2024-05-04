package com.cld.usuarios.services;

import com.cld.usuarios.models.GenericResponse;
import com.cld.usuarios.models.UsuarioGetResponse;
import com.cld.usuarios.models.UsuariosGetResponse;
import com.cld.usuarios.models.UsuariosPostResponse;
import com.cld.usuarios.models.dto.UsuarioDto;
import com.cld.usuarios.models.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {
     UsuariosGetResponse findAllUsers();
     UsuariosPostResponse createOne(UsuarioDto usuario);

     void updateOne(UsuarioDto usuario, String id);

     UsuarioGetResponse findUserById(String id);

     void deleteOne(String id);
}
