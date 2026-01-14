package org.example.soccer_manager.service.impl;

import org.example.soccer_manager.entity.Account;
import org.example.soccer_manager.repository.IAccountRepository;
import org.example.soccer_manager.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return null;
    }

    @Override
    public boolean save(Account account) {
        return false;
    }

    @Override
    public boolean update(Account account) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

}