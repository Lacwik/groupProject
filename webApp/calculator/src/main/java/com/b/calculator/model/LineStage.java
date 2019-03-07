package com.b.calculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "LINE_STAGE")
@Getter
@Setter
@NoArgsConstructor

public class LineStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "line_id")
    private Line line;

    @OneToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;
}
