package org.example.soccer_manager.service;

import lombok.RequiredArgsConstructor;
import org.example.soccer_manager.entity.NationalTeam;
import org.example.soccer_manager.repository.INationalTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NationalTeamService implements INationalTeamService {
    private final INationalTeamRepository nationalTeamRepository;

    @Override
    public List<NationalTeam> findAll() {
        return nationalTeamRepository.findAll();
    }
}
