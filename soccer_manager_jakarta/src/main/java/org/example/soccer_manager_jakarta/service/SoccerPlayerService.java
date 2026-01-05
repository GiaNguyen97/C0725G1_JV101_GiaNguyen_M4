package org.example.soccer_manager_jakarta.service;

import org.example.soccer_manager_jakarta.entity.SoccerPlayer;
import org.example.soccer_manager_jakarta.repository.ISoccerPlayerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SoccerPlayerService implements ISoccerPlayerService {
    private final ISoccerPlayerRepository soccerPlayerRepository;

    public SoccerPlayerService(ISoccerPlayerRepository soccerPlayerRepository) {
        this.soccerPlayerRepository = soccerPlayerRepository;
    }

    @Override
    public List<SoccerPlayer> findAll() {
        return soccerPlayerRepository.findAll();
    }

    @Override
    public SoccerPlayer findById(Long id) {
        return soccerPlayerRepository.findById(id);
    }

    @Override
    public boolean addNewPlayer(SoccerPlayer soccerPlayer) {
        return soccerPlayerRepository.addNewPlayer(soccerPlayer);
    }

    @Override
    public boolean editInfoPlayer(SoccerPlayer soccerPlayer) {
        return soccerPlayerRepository.editInfoPlayer(soccerPlayer);
    }

    @Override
    public boolean deleteById(Long id) {
        return soccerPlayerRepository.deleteById(id);
    }

    @Override
    public SoccerPlayer findByCode(String code) {
        return soccerPlayerRepository.findByCode(code);
    }

    @Override
    public List<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo) {
        return soccerPlayerRepository.search(name, dobFrom, dobTo);
    }
}
