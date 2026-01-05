package org.example.soccer_manager.service;

import lombok.RequiredArgsConstructor;
import org.example.soccer_manager.entity.SoccerPlayer;
import org.example.soccer_manager.repository.SoccerPlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SoccerPlayerService implements ISoccerPlayerService{
    private final SoccerPlayerRepository soccerPlayerRepository;

    @Override
    public List<SoccerPlayer> findAll() {
        return soccerPlayerRepository.findAll();
    }

    @Override
    public SoccerPlayer findById(Long id) {
        return soccerPlayerRepository.findById(id).orElse(null);
    }

    @Override
    public SoccerPlayer save(SoccerPlayer soccerPlayer) {
        return soccerPlayerRepository.save(soccerPlayer);
    }

    @Override
    public boolean deleteById(Long id) {
       if(soccerPlayerRepository.existsById(id)) {
           soccerPlayerRepository.deleteById(id);
           return true;
       }
       return false;
    }

    @Override
    public boolean existsByCodePlayer(String code) {
        return soccerPlayerRepository.existsByCodePlayer(code);
    }

    @Override
    public Page<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo, Pageable pageable) {
        return soccerPlayerRepository.search(name,dobFrom,dobTo,pageable);
    }
}
