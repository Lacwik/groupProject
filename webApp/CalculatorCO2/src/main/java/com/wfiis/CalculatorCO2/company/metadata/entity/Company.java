package com.wfiis.CalculatorCO2.company.metadata.entity;


import com.wfiis.CalculatorCO2.user.metadata.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "calc_companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "companiesWorker", cascade = CascadeType.ALL)
    private List<User> workers;

    @ManyToMany(mappedBy = "companiesExpert", cascade = CascadeType.ALL)
    private List<User> experts;

    @ManyToMany(mappedBy = "companiesAdmin", cascade = CascadeType.ALL)
    private List<User> administrators;
}
