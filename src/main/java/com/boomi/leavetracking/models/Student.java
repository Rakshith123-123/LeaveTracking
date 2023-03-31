package com.boomi.leavetracking.models;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "roll_no", unique = true)
    private String rollNo;

    @Column(name = "department")
    private String department;

    // getters and setters
}

