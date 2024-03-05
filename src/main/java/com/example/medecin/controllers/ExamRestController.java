package com.example.medecin.controllers;

import com.example.medecin.entities.*;
import com.example.medecin.repositories.CliniqueRepository;
import com.example.medecin.repositories.MedecinRepository;
import com.example.medecin.repositories.PatientRepository;
import com.example.medecin.repositories.RendezVousRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("rest")

public class ExamRestController {
    @Autowired
    CliniqueRepository cliniqueRepository;
    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    PatientRepository patientRepository;

   @PostMapping("/addClinique")
    public Clinique addClinique(@RequestBody Clinique clinique){
       return cliniqueRepository.save(clinique);
    }
    @PostMapping("/addMedecin/{cliniqueId}")
    public Medecin addMedecinAndAssignToClinique (@RequestBody Medecin medecin, @PathVariable Long cliniqueId){

       Clinique clinique = cliniqueRepository.findById(cliniqueId).orElse(null);
       clinique.getMedecins().add(medecin);
       return medecinRepository.save(medecin);

    }
    @PostMapping("/addPatient")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
   @PostMapping("/addRdv/{idMedecin}/{idPatient}")
    public void addRDVAndAssignMedAndPatient(@RequestBody RendezVous rdv,
                                             @PathVariable Long idMedecin,
                                             @PathVariable Long idPatient){
       Medecin medecin = medecinRepository.findById(idMedecin).orElse(null);

       Patient patient = patientRepository.findById(idPatient).orElse(null);

       rdv.setPatient(patient);
       rdv.setMedecin(medecin);
       rendezVousRepository.save(rdv);


    }

    @GetMapping("RdvByCliniqueSpecialite/{idClinique}/{specialite}")
    public List<RendezVous> getRendezVousByCliniqueAndSpecialite(@PathVariable Long idClinique, @PathVariable Specialite specialite){
      return rendezVousRepository.getRendezVousByCliniqueAndSpecialite(idClinique,specialite);

    }

    @GetMapping("NbrRdv/{idMedecin}")
    public int getNbrRendezVousMedecin(@PathVariable Long idMedecin){
       return rendezVousRepository.countByMedecinIdMedecin(idMedecin);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void retrieveRendezVous() {
        List<RendezVous> rdvs = rendezVousRepository.findAll();
        for (RendezVous RendezVous : rdvs) {
            if (RendezVous.getDateRDV().after(new Date()))
                log.info("La liste des RendezVous : " + RendezVous.getDateRDV() + " : Medecin :"
                        + RendezVous.getMedecin().getNomMedecin() + " : Patient :"
                        + RendezVous.getPatient().getNomPatient());
        }
    }









}
