package com.cld.usuarios.models.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {
    private Long id;
    @NotBlank
    @NotNull
    private Long idPersona;
    @NotBlank
    @NotNull
    private Date fechaRegistro;

    /*User status indicating the user's situation
    - Activo
    - Inactivo
    - Suspendido
    - Pendiente de activacion*/
    @NotBlank
    @NotNull
    private String estatus;

    /*username*/
    @NotBlank
    @NotNull
    private String correoElectronico;
    @NotBlank
    @NotNull
    private List<RolDto> roles;
    @NotBlank
    @NotNull
    private String contrasena;
}
