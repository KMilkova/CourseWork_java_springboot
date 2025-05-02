package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_payment", nullable = false)
    private Integer idPayment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_contract", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Contracts idContract;

    @Column(name = "payment_amount", nullable = true)
    private Integer paymentAmount;

    @Column(name = "date_of_payment", nullable = true)
    private Date dateOfPayment;

}
