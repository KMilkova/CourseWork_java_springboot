package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contracts")
public class Contracts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_contract", nullable = false)
    private Integer idContract;

    @Column(name = "contract_number", nullable = true)
    private Integer contractNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_registration")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private CarRegistration idRegistration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_space", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private ParkingSpace idSpace;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_manager", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Manager idManager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Status idStatus;

    @Column(name = "start_date_", nullable = true)
    private Date startDate;

    @Column(name = "end_date", nullable = true)
    private Date endDate;

}
