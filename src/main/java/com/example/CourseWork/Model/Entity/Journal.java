package com.example.CourseWork.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "journal")
public class Journal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_journal", nullable = false)
    private Integer idJournal;

    @Column(name = "entry_time", nullable = true)
    private Date entryTime;

    @Column(name = "action_", nullable = true, length = 50)
    private String action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_authorization")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Authorization idAuthorization;
}
