package com.b.calculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "MODULE")
@Getter
@Setter
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal loss;
    private BigDecimal power;
    private long time;

    @OneToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "module",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ModuleVegetable> moduleVegetables;


    @Column(name = "outsourced_company")
    private long outsourcedCompany;

    @Column(name = "outsourced_all")
    private long outsourcedAll;
}
