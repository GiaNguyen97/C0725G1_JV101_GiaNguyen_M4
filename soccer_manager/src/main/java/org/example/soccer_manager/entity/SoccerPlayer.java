package org.example.soccer_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.soccer_manager.validation.annotation.ValidAge;
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

    @Pattern(regexp = "^[A-Z]{2}-[0-9]{3}$", message = "Mã cầu thủ phải có định dạng 2 chữ cái viết hoa - 3 số (Ví dụ: VN-001)")
    @Column(unique = true)
    private String codePlayer;

    @Size(min = 5,max = 100,message = "Tên từ 5-100 ký tự")
    @Pattern(regexp = "^[a-zA-ZÀ-Ỵà-ỵ\\s]+$",
            message = "Tên không được chứa số hoặc ký tự đặc biệt")
    private String namePlayer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidAge
    private LocalDate dayOfBirth;

    @Positive(message = "Phải là số nguyên dương")
    private Integer experience;

    private String adress;

    private String position;

    private String urlImage;

    @Column(name = "player_status", columnDefinition = "TINYINT(1)")
    private boolean playerStatus = false;

    @ManyToOne
    @JoinColumn(name = "national_team_id")
    private NationalTeam nationalTeam;
}
