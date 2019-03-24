package com.b.calculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "STAGE")
@Getter
@Setter
@NoArgsConstructor
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "stage",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StageModule> modules;

    @OneToMany(mappedBy = "stage",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StageVegetable> vegetables;

    @OneToMany(mappedBy = "stage",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OutsourcedStage> companies;

    @Column(name = "outsourced")
    private long outsourced;
}
