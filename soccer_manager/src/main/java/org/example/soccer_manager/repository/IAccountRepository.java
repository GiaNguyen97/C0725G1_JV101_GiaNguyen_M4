package org.example.soccer_manager.repository;

import org.example.soccer_manager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountRepository extends JpaRepository<Account, String> {

    @Query(value = "select a from accounts a where a.username =:username and a.isDeleted is false ")
    Account findByUsernameAndIsDeletedIsFalse(@Param("username") String username);
}