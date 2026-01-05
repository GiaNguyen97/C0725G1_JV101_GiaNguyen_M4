package org.example.soccer_manager.service;

import org.example.soccer_manager.entity.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ISoccerPlayerService {
    List<SoccerPlayer> findAll();

    SoccerPlayer findById(Long id);

    SoccerPlayer save(SoccerPlayer soccerPlayer);


    boolean deleteById(Long id);

    boolean existsByCodePlayer(String code);

    Page<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo, Pageable pageable);
}
