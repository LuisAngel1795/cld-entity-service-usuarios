package com.cld.usuarios.dao;

import com.cld.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {

    List<Usuario>  findByEstatusNotEliminadoLogico();


}
