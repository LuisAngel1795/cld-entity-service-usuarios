package com.cld.usuarios.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
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
    @Column(name = "estatus")
    private String estatus;


    /*username*/
    @Column(name = "correo_electronico")
    private String correoElectronico;

    /*user password*/
    @Column(name = "contrasena")
    private String contrasena;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_usuarios",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns =@JoinColumn(name = "id_rol"))
    private List<Rol> roles;
}
