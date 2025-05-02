package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "manager")
public class Manager {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_manager", nullable = false)
    private Integer idManager;

    @Column(name = "fio_manager", nullable = true, length = 50)
    private String fioManager;

    @Column(name = "manager_email", nullable = true, length = 30)
    private String managerEmail;

    @Column(name = "telephone_number", nullable = true)
    private Integer telephoneNumber;

    @Column(name = "job_title", nullable = true, length = 30)
    private String jobTitle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Status idStatus;

}
