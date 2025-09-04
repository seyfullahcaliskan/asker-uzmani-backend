package com.askeruzmani.asker_uzmani_backend.Services;

import com.askeruzmani.asker_uzmani_backend.Entities.BankAccountsEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.BankAccountsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankAccountsService {

    @Autowired
    private  BankAccountsRepository bankAccountsRepository;

    public List<BankAccountsEntity> getAll() {
        return bankAccountsRepository.findAll();
    }

    public Optional<BankAccountsEntity> getOne(UUID id) {
        return bankAccountsRepository.findById(id);
    }

    public BankAccountsEntity save(BankAccountsEntity bankAccount) {
        return bankAccountsRepository.save(bankAccount);
    }

    public BankAccountsEntity update(UUID id, BankAccountsEntity bankAccount) {
        Optional<BankAccountsEntity> existing = bankAccountsRepository.findById(id);
        if (existing.isPresent()) {
            BankAccountsEntity toUpdate = existing.get();
            BeanUtils.copyProperties(bankAccount, toUpdate, "id");
            return bankAccountsRepository.save(toUpdate);
        } else {
            throw new RuntimeException("BankAccount not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<BankAccountsEntity> existing = bankAccountsRepository.findById(id);
        if (existing.isPresent()) {
            BankAccountsEntity toDelete = existing.get();
            toDelete.softDelete();
            bankAccountsRepository.save(toDelete);
        } else {
            throw new RuntimeException("BankAccount not found with id " + id);
        }
    }
}
