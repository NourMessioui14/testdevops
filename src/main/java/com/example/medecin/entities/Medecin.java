package com.example.medecin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Medecin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedecin;
    private String nomMedecin;
    private int telephone;
    private int prixConsultation;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    @OneToMany(mappedBy = "medecin",cascade = CascadeType.ALL)
    @JsonIgnore

    private List<RendezVous> rendezVous;
    @ManyToMany(mappedBy = "medecins")
    @JsonIgnore

    private List<Clinique> cliniques;



}
