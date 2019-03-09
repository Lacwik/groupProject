package com.b.calculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "LINE")
@Getter
@Setter
@NoArgsConstructor
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "line",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LineStage> lineStages;

    @Column(name = "outsourced")
    private long outsourced;
}
