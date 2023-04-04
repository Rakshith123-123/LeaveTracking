package com.boomi.leavetracking.models;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rollNo", referencedColumnName = "rollNo")
    private Student student;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "attendance")
    private String attendance;

    public Attendance() {
    }

    public Attendance(Student student, LocalDate date, String attendance) {
        this.student = student;
        this.date = date;
        this.attendance = attendance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent() {
        return student.getRollNo();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
