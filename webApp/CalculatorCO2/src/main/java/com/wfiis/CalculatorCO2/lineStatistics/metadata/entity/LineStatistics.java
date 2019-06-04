package com.wfiis.CalculatorCO2.lineStatistics.metadata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wfiis.CalculatorCO2.line.metadata.entity.Line;
import com.wfiis.CalculatorCO2.stageResourceValue.metadata.entity.StageResourceValue;
import com.wfiis.CalculatorCO2.vegetable.metadata.entity.Vegetable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "calc_line_statistics")
public class LineStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "line_id")
    @JsonBackReference
    private Line line;

    @ManyToMany
    @JoinTable(
            name = "calc_line_stages_resource_values",
            inverseJoinColumns = @JoinColumn(name = "statistics_id"),
            joinColumns = @JoinColumn(name = "stage_resource_value_id")
    )
    @JsonManagedReference
    private List<StageResourceValue> stageResourceValues;

    @ManyToOne
    @JoinColumn(name = "vegetable_id")
    @JsonBackReference
    private Vegetable vegetable;
}
