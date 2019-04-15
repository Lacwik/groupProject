package com.wfiis.CalculatorCO2.vegetable.metadata.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "calc_vegetables")
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    //TODO
    //stage_vegetable
    //module_vegetable
    //line_vegetable
}
