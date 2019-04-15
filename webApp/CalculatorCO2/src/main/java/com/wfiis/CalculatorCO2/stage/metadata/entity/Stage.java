package com.wfiis.CalculatorCO2.stage.metadata.entity;

import lombok.*;

import javax.persistence.*;

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

    //TODO
    //stage_vegetable
    //outsourced_stage
    //line_stage
    //company
}
