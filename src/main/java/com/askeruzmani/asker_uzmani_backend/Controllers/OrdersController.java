package com.askeruzmani.asker_uzmani_backend.Controllers;

import com.askeruzmani.asker_uzmani_backend.Controllers.api.Paths;
import com.askeruzmani.asker_uzmani_backend.Entities.Orders.OrderRequest;
import com.askeruzmani.asker_uzmani_backend.Entities.Orders.OrdersEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.CargoStatusEnum;
import com.askeruzmani.asker_uzmani_backend.Enums.PayStatusEnum;
import com.askeruzmani.asker_uzmani_backend.Repositories.OrdersRepository;
import com.askeruzmani.asker_uzmani_backend.Services.OrdersService;
import com.askeruzmani.asker_uzmani_backend.Services.PayTRService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(Paths.ORDERS)
public class OrdersController {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private PayTRService paytrService;

    @Autowired
    private ObjectMapper objectMapper;

    public static String generateOrderNo() {
        Random random = new Random();
        int number = 10000000 + random.nextInt(90000000);
        return "AU" + number;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderRequest request) throws JsonProcessingException {
        String merchantOid = UUID.randomUUID().toString().replace("-", "");

        OrdersEntity order = new OrdersEntity();
        order.setMerchantOid(merchantOid);
        order.setCustomerName(request.getCustomerName());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setCustomerPhone(request.getCustomerPhone());
        order.setCustomerAddress(request.getCustomerAddress());
        order.setItems(request.getItems());
        order.setAmount(request.getAmount());
        order.setPayStatus(PayStatusEnum.PENDING);
        order.setOrderNo(generateOrderNo());
        order.setCargoStatus(CargoStatusEnum.WAITING);
        orderRepository.save(order);

        return ResponseEntity.ok(Map.of(
                "merchantOid", merchantOid,
                "orderId", order.getId()
        ));
    }

    @PostMapping("/paytr/token")
    public ResponseEntity<Map<String, String>> getPaytrToken(@RequestBody Map<String, Object> payload,
                                                             HttpServletRequest request) throws Exception {
        String merchantOid = (String) payload.get("merchantOid");
        Optional<OrdersEntity> orderOpt = orderRepository.findByMerchantOid(merchantOid);

        if (orderOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Order not found"));
        }

        OrdersEntity order = orderOpt.get();

        payload.put("merchant_oid", order.getMerchantOid());
        payload.put("email", order.getCustomerEmail());
        payload.put("payment_amount", order.getAmount().multiply(BigDecimal.valueOf(100)).intValue());
        payload.put("user_name", order.getCustomerName());
        payload.put("user_address", order.getCustomerAddress());
        payload.put("user_phone", order.getCustomerPhone());
        payload.put("user_basket", order.getItems());

        return ResponseEntity.ok(paytrService.generateToken(payload, request));
    }

    @PostMapping("/paytr/notification")
    public ResponseEntity<String> paytrNotification(@RequestParam Map<String, String> post) throws Exception {
        return paytrService.handleNotification(post);
    }

    @GetMapping("/{merchantOid}")
    public ResponseEntity<?> getOrder(@PathVariable String merchantOid) {
        return orderRepository.findByMerchantOid(merchantOid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("get-one-by-order-no/{orderNo}")
    public ResponseEntity<?> getOrderWithOrderNo(@PathVariable String orderNo) {
        return orderRepository.findByOrderNo(orderNo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAll() {
        List<OrdersEntity> orders = orderRepository.findAllByOrderByDateOfRecordedDesc();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/set-cargo")
    public ResponseEntity<String> setCargo(@RequestBody Map<String, String> cargoData) throws Exception {
        return ordersService.setCargo(cargoData);
    }

    @PostMapping("/complete-order/{orderId}")
    public ResponseEntity<String> completeOrder(@PathVariable UUID orderId) throws Exception {
        return ordersService.completeOrder(orderId);
    }

    @PostMapping("/cancel-order/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable UUID orderId) throws Exception {
        return ordersService.cancelOrder(orderId);
    }

}
