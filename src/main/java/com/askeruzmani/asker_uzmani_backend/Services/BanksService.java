package com.askeruzmani.asker_uzmani_backend.Services;

import com.askeruzmani.asker_uzmani_backend.Entities.BanksEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.BanksRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BanksService {

    @Autowired
    private BanksRepository banksRepository;

    public List<BanksEntity> getAll() {
        return banksRepository.findAll();
    }

    public Optional<BanksEntity> getOne(UUID id) {
        return banksRepository.findById(id);
    }

    public BanksEntity save(BanksEntity bank) {
        return banksRepository.save(bank);
    }

    public BanksEntity update(UUID id, BanksEntity bank) {
        Optional<BanksEntity> existing = banksRepository.findById(id);
        if (existing.isPresent()) {
            BanksEntity toUpdate = existing.get();
            BeanUtils.copyProperties(bank, toUpdate, "id");
            return banksRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Bank not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<BanksEntity> existing = banksRepository.findById(id);
        if (existing.isPresent()) {
            BanksEntity toDelete = existing.get();
            toDelete.softDelete();
            banksRepository.save(toDelete);
        } else {
            throw new RuntimeException("Bank not found with id " + id);
        }
    }
}
