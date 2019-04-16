package com.wfiis.CalculatorCO2.resource.metadata.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "calc_resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private float water;

    @Column(nullable = false, unique = false)
    private float diesel;

    @Column(nullable = false, unique = false)
    private float lpg;

    @Column(nullable = false, unique = false)
    private float oil;
}
