package com.askeruzmani.asker_uzmani_backend.Services;

import com.askeruzmani.asker_uzmani_backend.Entities.Orders.OrdersEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.CargoStatusEnum;
import com.askeruzmani.asker_uzmani_backend.Enums.StatusEnum;
import com.askeruzmani.asker_uzmani_backend.Repositories.OrdersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public ResponseEntity<String> setCargo(Map<String, String> cargoData) throws Exception {
        String orderId = cargoData.get("orderId");
        String cargoCompany = cargoData.get("cargoCompany");
        String cargoCode = cargoData.get("cargoCode");

        Optional<OrdersEntity> orderOpt = ordersRepository.findById(UUID.fromString(orderId));
        if (!orderOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sipariş bulunamadı");
        }

        OrdersEntity order = orderOpt.get();
        order.setCargoCompany(cargoCompany);
        order.setCargoCode(cargoCode);
        order.setCargoStatus(CargoStatusEnum.IN_CARGO);
        ordersRepository.save(order);

        return ResponseEntity.ok("Kargo bilgisi kaydedildi");
    }

    public ResponseEntity<String> completeOrder(UUID orderId) throws Exception {

        Optional<OrdersEntity> orderOpt = ordersRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sipariş bulunamadı");
        }

        OrdersEntity order = orderOpt.get();
        order.setStatus(StatusEnum.COMPLETED);
        ordersRepository.save(order);
        return ResponseEntity.ok("Sipariş tamamlandı.");
    }

    public ResponseEntity<String> cancelOrder(UUID orderId) throws Exception {

        Optional<OrdersEntity> orderOpt = ordersRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sipariş bulunamadı");
        }

        OrdersEntity order = orderOpt.get();
        order.setStatus(StatusEnum.CANCELLED);
        ordersRepository.save(order);
        return ResponseEntity.ok("Sipariş iptal edildi.");
    }
}
