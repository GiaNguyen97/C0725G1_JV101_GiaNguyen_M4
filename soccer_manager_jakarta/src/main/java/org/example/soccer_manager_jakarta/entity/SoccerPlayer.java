package org.example.soccer_manager_jakarta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Parent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "soccer_player")
public class SoccerPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^MCT-[0-9]{3}$", message = "Sai định dạng MCT-XXX (X số từ 0-9)")
    @Column(name = "codePlayer", unique = true)
    private String codePlayer;

    @NotBlank(message = "Tên không được để trống")
    private String namePlayer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayOfBirth;

    private String experience;

    private String position;

    private String urlImage;

    public SoccerPlayer() {
    }

    public SoccerPlayer(String codePlayer, String namePlayer, LocalDate dayOfBirth, String experience, String position,
            String urlImage) {
        this.codePlayer = codePlayer;
        this.namePlayer = namePlayer;
        this.dayOfBirth = dayOfBirth;
        this.experience = experience;
        this.position = position;
        this.urlImage = urlImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePlayer() {
        return codePlayer;
    }

    public void setCodePlayer(String codePlayer) {
        this.codePlayer = codePlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
