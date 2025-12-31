package org.example.soccer_manager_jakarta.repository;


import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.soccer_manager_jakarta.entity.SoccerPlayer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SoccerPlayerRepository implements ISoccerPlayerRepository {


    @Override
    public List<SoccerPlayer> findAll() {
        String queryStr = "SELECT s FROM SoccerPlayer AS s";
        TypedQuery<SoccerPlayer> soccerPlayerTypedQuery = BaseRepository.entityManager.createQuery(queryStr, SoccerPlayer.class);
        return soccerPlayerTypedQuery.getResultList();
    }

    @Override
    public SoccerPlayer findById(Long id) {
        return BaseRepository.entityManager.find(SoccerPlayer.class,id);
    }

    @Override
    public boolean addNewPlayer(SoccerPlayer soccerPlayer) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        try {
            transaction.begin();
            BaseRepository.entityManager.persist(soccerPlayer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean editInfoPlayer(SoccerPlayer soccerPlayer) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        try {
            transaction.begin();
            BaseRepository.entityManager.merge(soccerPlayer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        EntityTransaction transaction = BaseRepository.entityManager.getTransaction();
        try {
            transaction.begin();
            SoccerPlayer soccerPlayer = findById(id);
            BaseRepository.entityManager.remove(soccerPlayer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
