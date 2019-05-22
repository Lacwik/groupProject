package com.wfiis.CalculatorCO2.module.metadata.entity;

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
    private Company company;

    @ManyToMany
    private List<Stage> stages;

    @OneToMany
    @JoinTable(
            name = "calc_module_vegetables",
            inverseJoinColumns = @JoinColumn(name = "module_id"),
            joinColumns = @JoinColumn(name = "vegetable_id")
    )
    private List<Vegetable> vegetables;

    @OneToMany
    @JoinTable(
            name = "calc_module_resources",
            inverseJoinColumns = @JoinColumn(name = "module_id"),
            joinColumns = @JoinColumn(name = "resource_id")
    )
    private List<Resource> resources;

    @OneToMany
    @JoinTable(
            name = "calc_module_leftovers",
            inverseJoinColumns = @JoinColumn(name = "module_id"),
            joinColumns = @JoinColumn(name = "leftover_id")
    )
    private List<Leftover> leftovers;
}
