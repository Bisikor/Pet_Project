package com.medical.accountingMedical.services;

import com.medical.accountingMedical.models.Medicals;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MedicalsService {

    public void save(Medicals medicals);

    public Iterable<Medicals> gelAll();

    public void deleteById(Long id);

    public Optional<Medicals> findById(Long id);
}
