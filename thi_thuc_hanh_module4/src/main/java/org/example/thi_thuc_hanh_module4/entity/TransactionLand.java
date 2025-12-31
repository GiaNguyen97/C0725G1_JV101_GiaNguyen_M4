package org.example.thi_thuc_hanh_module4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DialectOverride;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "giao_dich")
public class TransactionLand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransactionLand;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Mã giao dịch không được trống")
    private String codeTransactionLand;

    private LocalDate timeTransaction;

    private String service;
    private Float price;
    private Float area;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
