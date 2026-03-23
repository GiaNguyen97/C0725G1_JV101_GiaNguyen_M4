package org.example.thi_thuc_hanh_module_4_again.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ngày mua được để trống")
    @PastOrPresent(message = "Ngày mua không được lớn hơn hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng không được âm")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Sản phẩm không được để trống")
    private Product product;
}
