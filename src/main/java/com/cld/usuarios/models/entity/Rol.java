package com.cld.usuarios.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Roles")
@AllArgsConstructor()
@NoArgsConstructor
@Getter
@Setter
public class Rol implements Serializable {

    private static final long serialVersionUID = -4569251517938289381L;

    /*RoleId*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*Role's description*/
    @Column(name="descripcion")
    private String descripcion;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<Usuario> usuarios;


}
