package com.cld.usuarios.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@AllArgsConstructor()
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = -5110197638038559753L;

    /*user id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*person id*/
    @Column(name = "id_persona")
    private Long idPersona;

    /*user registration date*/
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    /*User status indicating the user's situation
        - Activo
        - Inactivo
        - Suspendido
        - Pendiente de activacion*/
    @Column(name = "activo")
    private String activo;


    /*username*/
    @Column(name = "usuario")
    private String usuario;

    /*user password*/
    @Column(name = "contrasena")
    private String contrasena;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Rol> roles;
}
