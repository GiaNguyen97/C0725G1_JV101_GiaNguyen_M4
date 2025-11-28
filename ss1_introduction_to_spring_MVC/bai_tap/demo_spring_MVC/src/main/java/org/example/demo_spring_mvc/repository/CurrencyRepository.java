package org.example.demo_spring_mvc.repository;

import org.example.demo_spring_mvc.entity.Currency;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrencyRepository implements ICurrencyRepository {

    private final Map<String, Double> rateMap = new HashMap<>();

    // Ở đây ta giả định repo biết các loại hợp lệ; nếu muốn load từ DB/API sau này thì sửa ở đây.
    @Override
    public double convert(Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency không được null");
        }

        double rate = currency.getRate();
        double amount = currency.getAmount();
        String type = currency.getType();

        if (rate <= 0 || amount < 0) {
            throw new IllegalArgumentException("Tỉ giá và số tiền phải lớn hơn 0!");
        }
        if ("usd-to-vnd".equals(type)) {
            return rate * amount;
        } else if ("vnd-to-usd".equals(type)) {
            return amount / rate;
        } else {
            throw new IllegalArgumentException("Loại chuyển đổi không hợp lệ: " + type);
        }
    }
}