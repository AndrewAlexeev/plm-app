package com.mai.projects.plm.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ_GENERATOR")
    @SequenceGenerator(name = "ROLE_SEQ_GENERATOR", sequenceName = "ROLE_SEQ", allocationSize = 10)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

}
