package com.medical.accountingMedical.repository;

import com.medical.accountingMedical.models.Medicals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalsRepository extends JpaRepository<Medicals, Long> {
}
