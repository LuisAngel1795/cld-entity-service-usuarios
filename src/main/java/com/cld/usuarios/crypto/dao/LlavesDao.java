package com.cld.usuarios.crypto.dao;


import com.cld.usuarios.crypto.models.Llaves;
import org.springframework.data.repository.CrudRepository;

public interface LlavesDao extends CrudRepository<Llaves, Long> {
}
