package org.example.soccer_manager.service;

import lombok.RequiredArgsConstructor;
import org.example.soccer_manager.entity.SoccerPlayer;
import org.example.soccer_manager.repository.ISoccerPlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class SoccerPlayerService implements ISoccerPlayerService {
    private final ISoccerPlayerRepository ISoccerPlayerRepository;

    @Override
    public SoccerPlayer findById(Long id) {
        return ISoccerPlayerRepository.findById(id).orElse(null);
    }

    @Override
    public SoccerPlayer save(SoccerPlayer soccerPlayer) {
        return ISoccerPlayerRepository.save(soccerPlayer);
    }

    @Override
    public boolean deleteById(Long id) {
        if (ISoccerPlayerRepository.existsById(id)) {
            ISoccerPlayerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByCodePlayer(String codePlayer) {
        return ISoccerPlayerRepository.existsByCodePlayer(codePlayer);
    }

    @Override
    public Page<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo, String searchPosition, Pageable pageable) {
        return ISoccerPlayerRepository.search(name, dobFrom, dobTo, searchPosition, pageable);
    }
}
