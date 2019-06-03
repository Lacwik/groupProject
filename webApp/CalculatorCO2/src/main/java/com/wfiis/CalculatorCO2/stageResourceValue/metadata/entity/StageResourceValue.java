package com.wfiis.CalculatorCO2.stageResourceValue.metadata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "calc_stage_resource_values")
public class StageResourceValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    @JsonBackReference
    private Resource resource;

    @Column(nullable = false, unique = false)
    private float value;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    @JsonBackReference
    private Stage stage;

    @Column(nullable = false, unique = false)
    private float time;
}
