package com.wfiis.CalculatorCO2.resource.metadata.entity;

import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "calc_resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private float power;

    @Column(nullable = false, unique = false)
    private float water;

    @Column(nullable = false, unique = false)
    private float diesel;

    @Column(nullable = false, unique = false)
    private float lpg;

    @Column(nullable = false, unique = false)
    private float oil;

    @OneToMany
    private List<Module> modules;
}
