package com.grammercetamol.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "appusers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        }
)
public class AppUsers {
    @Id
    @SequenceGenerator(
            name = "app_users_sequence",
            sequenceName = "app_users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "app_users_sequence"
    )
    private Long Id;
    @Column(
            name = "firstname",
            nullable = false,
            length = 50
    )
    private String firstName;
    @Column(
            name = "lastname",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "othername",
            nullable = false
    )
    private String otherName;

    @Column(
            name = "username"
    )
    private String username;


    @Column(
            nullable = false
    )
    private String password;


    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id"
            )
    )
    private Set<Role> roles;
    @Column(
            name = "date_of_birth"
    )
    private Date dob;

    public AppUsers(){
    }

    public AppUsers(String firstName,String lastName , String otherName, String username, String password, Date dob){
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.username = username;
        this.password = password;
        this.dob = dob;
    }
}
