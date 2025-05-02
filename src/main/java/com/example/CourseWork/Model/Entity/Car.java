package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="car")
public class Car {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_car", nullable = false)
    private Integer idCar;

    @Column(name = "car_number", nullable = true, length = 10)
    private String carNumber;

    @Column(name = "car_color", nullable = true, length = 30)
    private String carColor;

    @Column(name = "mileage", nullable = true)
    private Integer mileage;

    @Column(name = "year_of_issue", nullable = true)
    private Integer yearOfIssue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_information")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private CarInformation idInformation;

}
