package com.askeruzmani.asker_uzmani_backend.Services;

import com.askeruzmani.asker_uzmani_backend.Entities.Orders.OrdersEntity;
import com.askeruzmani.asker_uzmani_backend.Repositories.OrdersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    public List<OrdersEntity> getAll() {
        return ordersRepository.findAll();
    }

    public Optional<OrdersEntity> getOne(UUID id) {
        return ordersRepository.findById(id);
    }

    public OrdersEntity save(OrdersEntity order) {
        return ordersRepository.save(order);
    }

    public OrdersEntity update(UUID id, OrdersEntity order) {
        Optional<OrdersEntity> existing = ordersRepository.findById(id);
        if (existing.isPresent()) {
            OrdersEntity toUpdate = existing.get();
            BeanUtils.copyProperties(order, toUpdate, "id");
            return ordersRepository.save(toUpdate);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    public void delete(UUID id) {
        Optional<OrdersEntity> existing = ordersRepository.findById(id);
        if (existing.isPresent()) {
            OrdersEntity toDelete = existing.get();
            toDelete.softDelete();
            ordersRepository.save(toDelete);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

}
