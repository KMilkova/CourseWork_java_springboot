package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contractdocument")
public class Contractdocument {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_document", nullable = false)
    private Integer idDocument;

    @Column(name = "document_name", nullable = true, length = 60)
    private String documentName;

    @Column(name = "document_type", nullable = true, length = 60)
    private String documentType;

    @Column(name = "document_data", nullable = true)
    private byte[] documentData;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_owner", nullable = true)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private CarOwner idOwner;

}
