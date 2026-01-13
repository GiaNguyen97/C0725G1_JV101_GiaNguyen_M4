package org.example.soccer_manager.service;

import org.example.soccer_manager.entity.NationalTeam;

import java.util.List;

public interface INationalTeamService {
    List<NationalTeam> findAll();

    NationalTeam findById(Long id);
}
