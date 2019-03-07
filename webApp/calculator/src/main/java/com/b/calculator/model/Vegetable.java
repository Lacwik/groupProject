package com.b.calculator.model;

import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import javax.persistence.*;


@Entity
@Table(name = "VEGETABLE")
@Getter
@Setter
@NoArgsConstructor
public class Vegetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
