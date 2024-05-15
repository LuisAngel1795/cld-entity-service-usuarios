package com.cld.usuarios.crypto.services.impl;

import com.cld.usuarios.crypto.models.Llaves;
import org.springframework.stereotype.Service;

public interface ILlavesService {

    public Llaves generateLlaves();
    public Llaves getLlaves(Long id);

}
