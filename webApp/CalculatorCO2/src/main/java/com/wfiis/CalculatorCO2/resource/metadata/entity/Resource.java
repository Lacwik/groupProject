package com.wfiis.CalculatorCO2.resource.metadata.entity;

import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
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
    private String name;

    @Column(nullable = false, unique = false)
    private String gus;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
