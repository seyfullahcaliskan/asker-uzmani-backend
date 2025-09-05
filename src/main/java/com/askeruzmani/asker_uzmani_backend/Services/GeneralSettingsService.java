package com.askeruzmani.asker_uzmani_backend.Services;

import com.askeruzmani.asker_uzmani_backend.Entities.GeneralSettingsEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.GeneralSettingsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GeneralSettingsService {

    @Autowired
    GeneralSettingsRepository generalSettingsRepository;

    public List<GeneralSettingsEntity> getAll() {
        return generalSettingsRepository.findAll();
    }

    public Optional<GeneralSettingsEntity> getOne(UUID id) {
        return generalSettingsRepository.findById(id);
    }

    public GeneralSettingsEntity save(GeneralSettingsEntity settings) {
        return generalSettingsRepository.save(settings);
    }

    public GeneralSettingsEntity update(UUID id, GeneralSettingsEntity settings) {
        Optional<GeneralSettingsEntity> existing = generalSettingsRepository.findById(id);
        if (existing.isPresent()) {
            GeneralSettingsEntity toUpdate = existing.get();
            BeanUtils.copyProperties(settings, toUpdate, "id");
            return generalSettingsRepository.save(toUpdate);
        } else {
            throw new RuntimeException("settings not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<GeneralSettingsEntity> existing = generalSettingsRepository.findById(id);
        if (existing.isPresent()) {
            GeneralSettingsEntity toDelete = existing.get();
            toDelete.softDelete();
            generalSettingsRepository.save(toDelete);
        } else {
            throw new RuntimeException("settings not found with id " + id);
        }
    }
}
