package com.wfiis.CalculatorCO2.statistics.metadata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wfiis.CalculatorCO2.leftover.metadata.entity.Leftover;
import com.wfiis.CalculatorCO2.resource.metadata.entity.Resource;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "calc_line_statistics_stage_leftovers")
public class StatisticsStageLeftover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float value;

    @ManyToOne
    @JoinColumn(name = "leftover_id")
    private Leftover leftover;


    @ManyToOne
    @JoinColumn(name = "statistics_stage_id")
    @JsonBackReference
    private StatisticsStage statisticsStage;
}
