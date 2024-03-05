package com.example.medecin.repositories;

import com.example.medecin.entities.RendezVous;
import com.example.medecin.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

    @Query("SELECT r from RendezVous r join r.medecin.cliniques c where c.idClinique = :idClinique and r.medecin.specialite = :specialite")
    List<RendezVous> getRendezVousByCliniqueAndSpecialite(@Param("idClinique") Long idClinique,@Param("specialite") Specialite specialite);

    int countByMedecinIdMedecin(Long idMedecin);



}