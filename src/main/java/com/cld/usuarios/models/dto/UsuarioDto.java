package com.cld.usuarios.models.dto;

import com.cld.usuarios.models.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto{
        private Long id;
        private Long idPersona;
        private Date fechaRegistro;
        /*User status indicating the user's situation
        - Activo
        - Inactivo
        - Suspendido
        - Pendiente de activacion*/
       private String estatus;
        /*username*/
        private String correoElectronico;
        private List<RolDto> roles;
 }
