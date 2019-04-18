package com.wfiis.CalculatorCO2.line.metadata.entity;

import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "calc_lines")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private int outsourced;

    @Column(nullable = false, unique = false)
    private Long companyId;

    @ManyToMany
    @JoinTable(
            name = "LINE_STAGES",
            inverseJoinColumns = @JoinColumn(name = "line_id"),
            joinColumns = @JoinColumn(name = "stage_id")
    )
    private List<Stage> stages;

    @ManyToMany
    @JoinTable(
            name = "LINE_VEGETABLES",
            inverseJoinColumns = @JoinColumn(name = "line_id"),
            joinColumns = @JoinColumn(name = "vegetable_id")
    )
    private List<Vegetable> vegetables;
}
