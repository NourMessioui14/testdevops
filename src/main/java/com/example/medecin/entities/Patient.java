package com.example.medecin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idPatient;
    private String nomPatient;
    private int telephone;
    @Temporal(TemporalType.DATE)

    private Date dateNaissance;


    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RendezVous> rendezVous;

}
