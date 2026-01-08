package org.example.soccer_manager.service;

import org.example.soccer_manager.entity.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ISoccerPlayerService {

    SoccerPlayer findById(Long id);

    SoccerPlayer save(SoccerPlayer soccerPlayer);

    boolean deleteById(Long id);

    boolean existsByCodePlayer(String codePlayer);

    Page<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo,String searchPosition, Pageable pageable);
}
