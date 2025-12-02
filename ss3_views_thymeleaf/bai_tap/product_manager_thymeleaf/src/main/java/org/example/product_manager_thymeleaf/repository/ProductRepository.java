package org.example.product_manager_thymeleaf.repository;

import org.example.product_manager_thymeleaf.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository implements IProductRepository {
    private static final Map<Integer, Product> products = new HashMap<>();

    static {
        products.put(1, new Product(1, "IPhone 15", 25000000, "Flagship Apple", "Apple"));
        products.put(2, new Product(2, "Samsung S24", 23000000, "Samsung cao cấp", "Samsung"));

        products.put(3, new Product(3, "Xiaomi 14 Ultra", 21000000, "Camera mạnh, hiệu năng cao", "Xiaomi"));
        products.put(4, new Product(4, "Oppo Find X7", 19000000, "Thiết kế đẹp, sạc nhanh", "Oppo"));
        products.put(5, new Product(5, "Huawei P60 Pro", 24000000, "Chụp hình xuất sắc", "Huawei"));
        products.put(6, new Product(6, "Vivo X100 Pro", 22000000, "Zeiss camera, hiệu năng mạnh", "Vivo"));
        products.put(7, new Product(7, "Google Pixel 8 Pro", 26000000, "Camera AI, Android thuần", "Google"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public boolean save(Product product) {
        products.put(product.getId(), product);
        return true;
    }

    @Override
    public boolean update(int id, Product product) {
        product.setId(id);
        products.put(id, product);
        return true;
    }

    @Override
    public boolean delete(int id) {
        products.remove(id);
        return true;
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        String lowerName = name.toLowerCase();

        for (Product p : products.values()) {
            if (p.getName().toLowerCase().contains(lowerName)) {
                result.add(p);
            }
        }

        return result;
    }
}

