package org.example.soccer_manager.repository;

import org.example.soccer_manager.entity.NationalTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INationalTeamRepository extends JpaRepository<NationalTeam,Long> {

}
