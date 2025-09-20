package com.askeruzmani.asker_uzmani_backend.Services;

import com.askeruzmani.asker_uzmani_backend.Entities.Orders.OrdersEntity;
import com.askeruzmani.asker_uzmani_backend.Enums.PayStatusEnum;
import com.askeruzmani.asker_uzmani_backend.Repositories.OrdersRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PayTRService {

    private final OrdersRepository ordersRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${paytr.merchant_id}")
    private String merchantId;
    @Value("${paytr.merchant_key}")
    private String merchantKey;
    @Value("${paytr.merchant_salt}")
    private String merchantSalt;
    @Value("${paytr.merchant_ok_url}")
    private String merchantOkUrl;
    @Value("${paytr.merchant_fail_url}")
    private String merchantFailUrl;

    public Map<String, String> generateToken(Map<String, Object> payload, HttpServletRequest request) throws Exception {
        String merchantOid = (String) payload.get("merchant_oid");
        String email = (String) payload.get("email");
        Integer paymentAmount = (Integer) payload.get("payment_amount");

        String basketJson = objectMapper.writeValueAsString(payload.get("user_basket")); // örn: [["Ürün 1","123.00",1]]
        String userBasket = Base64.getEncoder().encodeToString(basketJson.getBytes(StandardCharsets.UTF_8));
        payload.put("user_basket", userBasket);

        String userName = (String) payload.getOrDefault("user_name", "");
        String userAddress = (String) payload.getOrDefault("user_address", "");
        String userPhone = (String) payload.getOrDefault("user_phone", "");

        String userIp = extractClientIp(request);

        int noInstallment = 0;
        int maxInstallment = 0;
        String currency = "TL";
        int testMode = Optional.ofNullable(payload.get("test_mode"))
                .map(Object::toString)
                .map(Integer::parseInt)
                .orElse(0);
        String hashStr = merchantId + userIp + merchantOid + email + paymentAmount + userBasket + noInstallment + maxInstallment + currency + testMode;
        String paytrToken = base64HmacSha256(hashStr + merchantSalt, merchantKey);

        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("merchant_id", merchantId);
        form.add("user_ip", userIp);
        form.add("merchant_oid", merchantOid);
        form.add("email", email);
        form.add("payment_amount", String.valueOf(paymentAmount));
        form.add("paytr_token", paytrToken);
        form.add("user_basket", userBasket);
        form.add("no_installment", String.valueOf(noInstallment));
        form.add("max_installment", String.valueOf(maxInstallment));
        form.add("user_name", userName);
        form.add("user_address", userAddress);
        form.add("user_phone", userPhone);
        form.add("merchant_ok_url", merchantOkUrl);
        form.add("merchant_fail_url", merchantFailUrl);
        form.add("currency", currency);
        form.add("test_mode", String.valueOf(testMode));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(form, headers);

        ResponseEntity<String> response = rest.postForEntity(
                "https://www.paytr.com/odeme/api/get-token",
                entity,
                String.class
        );

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new IllegalStateException("PayTR token isteği başarısız");
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> body = mapper.readValue(response.getBody(), new TypeReference<>() {});

        if (!"success".equals(body.get("status"))) {
            throw new IllegalStateException("PayTR hata: " + body.get("reason"));
        }

        return Collections.singletonMap("token", body.get("token").toString());
    }

    public ResponseEntity<String> handleNotification(Map<String, String> post) {
        String merchantOid = post.get("merchant_oid");
        String status = post.get("status");

        OrdersEntity order = ordersRepository.findByMerchantOid(merchantOid)
                .orElseThrow(() -> new RuntimeException("Order not found: " + merchantOid));

        if ("success".equals(status)) {
            order.setPayStatus(PayStatusEnum.SUCCESS);
        } else {
            order.setPayStatus(PayStatusEnum.FAILED);
        }
        ordersRepository.save(order);

        return ResponseEntity.ok("OK"); // PayTR OK bekliyor
    }

    private String extractClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    private String base64HmacSha256(String data, String key) throws Exception {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256Hmac.init(secretKey);
        return Base64.getEncoder().encodeToString(sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }
}
