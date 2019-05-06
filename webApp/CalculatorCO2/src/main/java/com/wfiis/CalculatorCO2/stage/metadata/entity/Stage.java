package com.wfiis.CalculatorCO2.stage.metadata.entity;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.resourceFlags.metadata.entity.ResourceFlags;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
@Table(name = "calc_stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private float power;

    @Column(nullable = false, unique = false)
    private float loss;

    @Column(nullable = false, unique = false)
    private float waste;

    @ManyToOne
    @JoinColumn(name = "resource_flags_id")
    private ResourceFlags resourceFlags;

    @Column(nullable = false, unique = false)
    private Boolean outsourced;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "STAGE_VEGETABLES",
            inverseJoinColumns = @JoinColumn(name = "stage_id"),
            joinColumns = @JoinColumn(name = "vegetable_id")
    )
    private List<Vegetable> vegetables;

    @ManyToMany
    @JoinTable(
            name = "STAGE_MODULES",
            inverseJoinColumns = @JoinColumn(name = "stage_id"),
            joinColumns = @JoinColumn(name = "module_id")
    )
    private List<Module> modules;

    @ManyToMany
    private List<Line> lines;

    @Column(nullable = false, unique = false)
    private Boolean unused;
}
