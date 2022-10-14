package com.papeleria.soho.papeleriasoho.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "Roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ERole name;

}
