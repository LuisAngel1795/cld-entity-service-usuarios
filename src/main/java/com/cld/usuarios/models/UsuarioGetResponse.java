package com.cld.usuarios.models;

import com.cld.usuarios.models.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGetResponse {
    private UsuarioDto usuario;
}