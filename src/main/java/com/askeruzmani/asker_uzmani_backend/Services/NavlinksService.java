package com.askeruzmani.asker_uzmani_backend.Services;

import com.askeruzmani.asker_uzmani_backend.Entities.NavlinksEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum;
import com.askeruzmani.asker_uzmani_backend.Repositories.NavlinksRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NavlinksService {

    @Autowired
    private NavlinksRepository navlinksRepository;

    public List<NavlinksEntity> getAll() {
        return navlinksRepository.findByStatusOrderBySequenceNoAsc(StatusEnum.ACTIVE);
    }

    public Optional<NavlinksEntity> getOne(UUID id) {
        return navlinksRepository.findById(id);
    }

    public NavlinksEntity save(NavlinksEntity navlink) {
        return navlinksRepository.save(navlink);
    }

    public NavlinksEntity update(UUID id, NavlinksEntity navlink) {
        Optional<NavlinksEntity> existing = navlinksRepository.findById(id);
        if (existing.isPresent()) {
            NavlinksEntity toUpdate = existing.get();
            BeanUtils.copyProperties(navlink, toUpdate, "id");
            return navlinksRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Navlink not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<NavlinksEntity> existing = navlinksRepository.findById(id);
        if (existing.isPresent()) {
            NavlinksEntity toDelete = existing.get();
            toDelete.softDelete();
            navlinksRepository.save(toDelete);
        } else {
            throw new RuntimeException("Navlink not found with id " + id);
        }
    }
}
