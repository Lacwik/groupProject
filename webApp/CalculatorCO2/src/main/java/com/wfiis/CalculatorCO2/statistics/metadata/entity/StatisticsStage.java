package com.wfiis.CalculatorCO2.statistics.metadata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wfiis.CalculatorCO2.stage.metadata.entity.Stage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "calc_line_statistics_stage")
public class StatisticsStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(cascade= CascadeType.ALL)
    private List<StatisticsStageResource> stageResources;

    @OneToMany(cascade=CascadeType.ALL)
    private List<StatisticsStageLeftover> stageLeftovers;

    private Long time;
    private Float carbonPrint;
}
