package org.example.quan_ly_heo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pigs")
@Getter
@Setter
public class Pig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Mã heo không được trống")
    private String code; // mã số heo

    private LocalDateTime importTime;
    @Min(value = 10, message = "Trọng lượng phải >= 10kg")
    private Double importWeight;

    private LocalDateTime exportTime;
    private Double exportWeight;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;

    public boolean isSold() {
        return exportTime != null;
    }
}