package com.wfiis.CalculatorCO2.module.metadata.entity;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "calc_modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private float loss;

    @Column(nullable = false, unique = false)
    private float waste;

    @Column(nullable = false, unique = false)
    private float power;

    @Column(nullable = false, unique = false)
    private int time;

    @Column(nullable = false, unique = false)
    private int outsourced;

    private Resource resource;

    private Company company;

    @ManyToMany
    @JoinTable(
            name = "MODULE_VEGETABLES",
            inverseJoinColumns = @JoinColumn(name = "module_id"),
            joinColumns = @JoinColumn(name = "vegetable_id")
    )
    private List<Vegetable> vegetables;

    //TODO
    //outsourced module ??
}
