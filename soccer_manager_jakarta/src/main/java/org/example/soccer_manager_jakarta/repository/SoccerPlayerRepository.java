package org.example.soccer_manager_jakarta.repository;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.soccer_manager_jakarta.entity.SoccerPlayer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class SoccerPlayerRepository implements ISoccerPlayerRepository {

    @Override
    public List<SoccerPlayer> findAll() {
        String queryStr = "SELECT s FROM SoccerPlayer AS s";
        TypedQuery<SoccerPlayer> soccerPlayerTypedQuery = BaseRepository.entityManager.createQuery(queryStr,
                SoccerPlayer.class);
        return soccerPlayerTypedQuery.getResultList();
    }

    @Override
    public SoccerPlayer findById(Long id) {
        return BaseRepository.entityManager.find(SoccerPlayer.class, id);
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

    @Override
    public SoccerPlayer findByCode(String code) {
        try {
            String queryStr = "SELECT s FROM SoccerPlayer AS s WHERE s.codePlayer = :code";
            TypedQuery<SoccerPlayer> query = BaseRepository.entityManager.createQuery(queryStr, SoccerPlayer.class);
            query.setParameter("code", code);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SoccerPlayer> search(String name, LocalDate dobFrom, LocalDate dobTo) {
        StringBuilder queryStr = new StringBuilder("SELECT s FROM SoccerPlayer AS s WHERE 1=1");
        if (name != null && !name.isEmpty()) {
            queryStr.append(" AND s.namePlayer LIKE :name");
        }
        if (dobFrom != null) {
            queryStr.append(" AND s.dayOfBirth >= :dobFrom");
        }
        if (dobTo != null) {
            queryStr.append(" AND s.dayOfBirth <= :dobTo");
        }

        TypedQuery<SoccerPlayer> query = BaseRepository.entityManager.createQuery(queryStr.toString(),
                SoccerPlayer.class);

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (dobFrom != null) {
            query.setParameter("dobFrom", dobFrom);
        }
        if (dobTo != null) {
            query.setParameter("dobTo", dobTo);
        }

        return query.getResultList();
    }
}
