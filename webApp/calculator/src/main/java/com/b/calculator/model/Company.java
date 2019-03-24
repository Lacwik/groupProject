package com.b.calculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "worker",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CompanyWorker> workers;

    @OneToMany(mappedBy = "expert",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CompanyExpert> experts;
}
