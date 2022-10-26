package com.grammercetamol.models;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = IDENTITY,
            generator = "role_sequence"
    )
    private Integer id;

    @Enumerated(STRING)
    @Column(length = 20)
    private ERole name;
}
