package org.example.soccer_manager_jakarta.repository;

import org.example.soccer_manager_jakarta.entity.SoccerPlayer;

import java.time.LocalDate;
import java.util.List;

public interface ISoccerPlayerRepository {
    List<SoccerPlayer> findAll();

    SoccerPlayer findById(Long id);

    boolean addNewPlayer(SoccerPlayer soccerPlayer);

    boolean editInfoPlayer(SoccerPlayer soccerPlayer);

    boolean deleteById(Long id);

    SoccerPlayer findByCode(String code);

    List<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo);
}
