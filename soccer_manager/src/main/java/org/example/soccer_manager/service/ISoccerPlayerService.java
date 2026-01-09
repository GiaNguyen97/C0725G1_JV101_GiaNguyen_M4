package org.example.soccer_manager.service;

import org.example.soccer_manager.entity.SoccerPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ISoccerPlayerService {

    SoccerPlayer findById(Long id);

    SoccerPlayer save(SoccerPlayer soccerPlayer);

    boolean deleteById(Long id);

    boolean existsByCodePlayer(String codePlayer);

    boolean existsByCodePlayerAndIdNot(String codePlayer, Long id);

    void validateCodePlayer(String codePlayer, String countryCode);

    List<SoccerPlayer> findAllByPlayerStatus(boolean playerStatus);

    Page<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo, String searchPosition,
            Pageable pageable);

    List<SoccerPlayer> findAllById(Iterable<Long> ids);
}
