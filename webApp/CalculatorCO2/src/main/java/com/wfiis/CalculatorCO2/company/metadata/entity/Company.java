package com.wfiis.CalculatorCO2.company.metadata.entity;


import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "calc_companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "COMPANIES_WORKERS",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "company_id")
    )
    private List<User> workers;

    @ManyToMany
    @JoinTable(
            name = "COMPANIES_EXPERTS",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "company_id")
    )
    private List<User> experts;

    @ManyToMany
    @JoinTable(
            name = "COMPANIES_ADMIN",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "company_id")
    )
    private List<User> administrators;

    @OneToMany
    private List<Module> modules;

    @OneToMany
    private List<Stage> stages;

    @OneToMany
    private List<Line> lines;
}
