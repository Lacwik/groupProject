package com.wfiis.CalculatorCO2.leftover.metadata.entity;

import com.wfiis.CalculatorCO2.module.metadata.entity.Module;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "calc_leftovers")
public class Leftover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
