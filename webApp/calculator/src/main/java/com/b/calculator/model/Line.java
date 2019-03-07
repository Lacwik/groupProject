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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "line",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<LineStage> lineStages;

    @JoinColumn(name = "outsourced_company")
    private long outsourcedCompany;

    @JoinColumn(name = "outsourced_company")
    private long outsourcedAll;
}
