package com.example.medecin.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RendezVous implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRDV;
    @Temporal(TemporalType.DATE)

    private Date dateRDV;
    private String remarque;

    @ManyToOne
    private Medecin medecin;

    @ManyToOne
    private Patient patient;

}
