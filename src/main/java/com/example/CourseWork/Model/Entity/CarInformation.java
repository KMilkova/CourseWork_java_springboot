package com.example.CourseWork.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carinformation")
public class CarInformation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_information", nullable = false)
    private Integer idInformation;

    @Column(name = "car_brand", nullable = true, length = 30)
    private String carBrand;

    @Column(name = "engine_type", nullable = true, length = 30)
    private String engineType;

    @Column(name = "type_of_shell", nullable = true, length = 30)
    private String typeOfShell;

}
