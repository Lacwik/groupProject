package com.wfiis.CalculatorCO2.stage.metadata.entity;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
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
@Table(name = "calc_stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private int outsourced;

    @Column(nullable = false, unique = false)
    private Long companyId;

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
}
