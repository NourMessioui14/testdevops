package com.example.medecin.repositories;

import com.example.medecin.entities.Clinique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CliniqueRepository extends JpaRepository<Clinique,Long> {
}
