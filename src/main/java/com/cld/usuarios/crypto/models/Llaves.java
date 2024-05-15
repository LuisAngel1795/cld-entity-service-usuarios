package com.cld.usuarios.crypto.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "llaves")
public class Llaves {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "llaves_id_seq")
    private Long id;
    @Column(name = "expiration_date_time")
    private String expirationDateTime;
    @Column(name = "acceso_simetrico")
    private String accesoSimetrico;
    @Column(name = "codigoHash")
    private String codigoHash;
}
