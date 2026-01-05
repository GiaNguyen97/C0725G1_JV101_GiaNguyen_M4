package org.example.soccer_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "soccer_player")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SoccerPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z]{3}-[0-9]{3}$", message = "Mã cầu thủ phải có định dạng 3 chữ cái viết hoa - 3 số (Ví dụ: GER-001)")
    @Column(unique = true)
    private String codePlayer;

    @NotBlank(message = "Tên không được để trống")
    private String namePlayer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayOfBirth;

    private Integer experience;

    private String adress;

    private String position;

    private String urlImage;
}
