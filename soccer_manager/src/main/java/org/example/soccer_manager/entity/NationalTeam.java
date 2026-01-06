package org.example.soccer_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "national_team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NationalTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameNationalTeam;

    private String countryCode;

    private String logoUrl;
}
