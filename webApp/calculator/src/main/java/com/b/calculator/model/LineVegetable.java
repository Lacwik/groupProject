package com.b.calculator.model;

import javax.persistence.*;

public class LineVegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "line_id")
    private Line line;

    @OneToOne
    @JoinColumn(name = "vegetable_id")
    private Vegetable vegetable;
}
