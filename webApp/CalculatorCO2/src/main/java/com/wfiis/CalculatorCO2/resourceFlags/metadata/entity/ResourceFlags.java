package com.wfiis.CalculatorCO2.resourceFlags.metadata.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "calc_resources_flags")
public class ResourceFlags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private Boolean waterFlag;

    @Column(nullable = false, unique = false)
    private Boolean dieselFlag;

    @Column(nullable = false, unique = false)
    private Boolean lpgFlag;

    @Column(nullable = false, unique = false)
    private Boolean foilFlag;
}
