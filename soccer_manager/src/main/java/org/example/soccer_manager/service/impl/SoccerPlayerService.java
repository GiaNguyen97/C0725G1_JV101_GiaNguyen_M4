package org.example.soccer_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.soccer_manager.entity.SoccerPlayer;
import org.example.soccer_manager.exception.MaxPlayerExceededException;
import org.example.soccer_manager.repository.ISoccerPlayerRepository;
import org.example.soccer_manager.service.ISoccerPlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SoccerPlayerService implements ISoccerPlayerService {
    private final ISoccerPlayerRepository soccerPlayerRepository;

    @Override
    public SoccerPlayer findById(Long id) {
        return soccerPlayerRepository.findById(id).orElse(null);
    }

    @Override
    public List<SoccerPlayer> findAll() {
        return soccerPlayerRepository.findAll();
    }

    @Override
    public SoccerPlayer findById(Integer id) {
        return null;
    }

    @Override
    public boolean save(SoccerPlayer soccerPlayer) {
        if (soccerPlayer.isPlayerStatus() != soccerPlayerRepository.findById(soccerPlayer.getId()).orElse(null).isPlayerStatus()) {
            if (soccerPlayer.isPlayerStatus()) {
                if (soccerPlayerRepository.countAllByPlayerStatus(true) >= 11) {
                    throw new MaxPlayerExceededException();
                }
            }
        }
        return soccerPlayerRepository.save(soccerPlayer) != null;
    }

    @Override
    public boolean update(SoccerPlayer soccerPlayer) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if (soccerPlayerRepository.existsById(id)) {
            soccerPlayerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByCodePlayer(String codePlayer) {
        return soccerPlayerRepository.existsByCodePlayer(codePlayer);
    }

    @Override
    public boolean existsByCodePlayerAndIdNot(String codePlayer, Long id) {
        return soccerPlayerRepository.existsByCodePlayerAndIdNot(codePlayer, id);
    }

    @Override
    public void validateCodePlayer(String codePlayer, String countryCode) {
        if (!codePlayer.substring(0, 2).equals(countryCode)) {
            throw new IllegalArgumentException("Vui lòng nhập đúng mã quốc gia bạn chọn " + countryCode);
        }
    }

    @Override
    public List<SoccerPlayer> findAllByPlayerStatus(boolean playerStatus) {
        return soccerPlayerRepository.findAllByPlayerStatus(playerStatus);
    }

    @Override
    public Page<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo, String searchPosition,
                                     Pageable pageable) {
        return soccerPlayerRepository.search(name, dobFrom, dobTo, searchPosition, pageable);
    }

    @Override
    public List<SoccerPlayer> findAllById(Iterable<Long> ids) {
        return soccerPlayerRepository.findAllById(ids);
    }
}
