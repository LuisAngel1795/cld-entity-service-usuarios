package com.cld.usuarios.dao;

import com.cld.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    List<Usuario>  findByEstatusNot(String estatus);


}
