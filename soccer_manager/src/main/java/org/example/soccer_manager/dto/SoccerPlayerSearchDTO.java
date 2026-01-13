package org.example.soccer_manager.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SoccerPlayerSearchDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dobFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dobTo;

    private String searchPosition;

    private int page = 0;
    private int size = 4; // Default size updated to 4 as seen in view
}
