package com.medical.accountingMedical.services;

import com.medical.accountingMedical.models.Medicals;
import com.medical.accountingMedical.repository.MedicalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalsServiceImpl implements MedicalsService {

    @Autowired
    MedicalsRepository repository;

    @Override
    public void save(Medicals medicals) {
        repository.save(medicals);
    }

    @Override
    public Iterable<Medicals> gelAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Medicals> findById(Long id) {
        return repository.findById(id);
    }
}
