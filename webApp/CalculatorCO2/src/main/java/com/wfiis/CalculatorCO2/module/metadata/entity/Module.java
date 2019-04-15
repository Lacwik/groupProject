package com.wfiis.CalculatorCO2.module.metadata.entity;

import lombok.*;

import javax.persistence.*;

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

    //TODO
    //stage_module
    //module_vegetable
    //resource
    //company
}
