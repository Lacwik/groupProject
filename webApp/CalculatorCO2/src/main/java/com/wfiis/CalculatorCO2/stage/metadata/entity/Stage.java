package com.wfiis.CalculatorCO2.stage.metadata.entity;

import com.wfiis.CalculatorCO2.company.metadata.entity.Company;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
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
    private Boolean outsourced;

    @Column(nullable = false, unique = false)
    private Boolean used;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    private List<Line> lines;

    @ManyToMany
    @JoinTable(
            name = "stage_modules",
            inverseJoinColumns = @JoinColumn(name = "stage_id"),
            joinColumns = @JoinColumn(name = "module_id")
    )
    private List<Module> modules;
}
