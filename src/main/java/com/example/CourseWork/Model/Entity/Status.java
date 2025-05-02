package com.example.CourseWork.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "status_", schema = "parking")
public class Status {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_status", nullable = false)
    private Integer idStatus;
    @Basic
    @Column(name = "status_type", nullable = true, length = 50)
    private String statusType;
}
