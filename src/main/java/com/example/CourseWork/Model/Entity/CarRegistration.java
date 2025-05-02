package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carregistration")
public class CarRegistration {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_registration", nullable = false)
    private Integer idRegistration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_owner", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private CarOwner idOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Car idCar;

    @Column(name = "start_date_", nullable = true)
    private Date startDate;

    @Column(name = "end_date", nullable = true)
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Status idStatus;

}
