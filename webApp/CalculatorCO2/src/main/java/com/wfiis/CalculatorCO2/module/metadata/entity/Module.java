package com.wfiis.CalculatorCO2.module.metadata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "calc_modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private float power;

    @Column(nullable = false, unique = false)
    private Boolean outsourced;

    @Column(nullable = false, unique = false)
    private Boolean used;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "calc_stage_modules",
            inverseJoinColumns = @JoinColumn(name = "module_id"),
            joinColumns = @JoinColumn(name = "stage_id")
    )
    @JsonBackReference
    private List<Stage> stages;

    @ManyToMany
    @JoinTable(
            name = "calc_module_vegetables",
            inverseJoinColumns = @JoinColumn(name = "vegetable_id"),
            joinColumns = @JoinColumn(name = "module_id")
    )
    private List<Vegetable> vegetables;

    @ManyToMany
    @JoinTable(
            name = "calc_module_resources",
            inverseJoinColumns = @JoinColumn(name = "resource_id"),
            joinColumns = @JoinColumn(name = "module_id")
    )
    private List<Resource> resources;

    @ManyToMany
    @JoinTable(
            name = "calc_module_leftovers",
            inverseJoinColumns = @JoinColumn(name = "leftover_id"),
            joinColumns = @JoinColumn(name = "module_id")
    )
    private List<Leftover> leftovers;
}
