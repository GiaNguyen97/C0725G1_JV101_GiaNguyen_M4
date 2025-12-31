package org.example.thi_thuc_hanh_module4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "khach_hang")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Mã khách hàng không được trống")
    private String codeCustomer;

    private String name;
    private String phoneNumber;
    private String email;
}
