package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "carowner")
public class CarOwner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_owner", nullable = false)
    private Integer idOwner;

    @Column(name = "full_name", nullable = true, length = 50)
    private String fullName;

    @Column(name = "telephone_number", nullable = true)
    private Integer telephoneNumber;

    @Column(name = "owner_email", nullable = true, length = 30)
    private String ownerEmail;

    @Column(name = "gender", nullable = true, length = 30)
    private String gender;

}
