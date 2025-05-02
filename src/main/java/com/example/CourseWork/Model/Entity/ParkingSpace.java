package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parkingspace")
public class ParkingSpace {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_space", nullable = false)
    private Integer idSpace;

    @Column(name = "floor_", nullable = true)
    private Integer floor;

    @Column(name = "place_number", nullable = true)
    private Integer placeNumber;

    @Column(name = "place_size", nullable = true)
    private Integer placeSize;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Status idStatus;


}
