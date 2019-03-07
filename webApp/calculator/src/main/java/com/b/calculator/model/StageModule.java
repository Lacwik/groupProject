package com.b.calculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "STAGE_MODULE")
@Getter
@Setter
@NoArgsConstructor
public class StageModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
