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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "stage",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StageModule> stageModules;

    @JoinColumn(name = "outsourced_company")
    private long outsourcedCompany;

    @JoinColumn(name = "outsourced_company")
    private long outsourcedAll;
}
