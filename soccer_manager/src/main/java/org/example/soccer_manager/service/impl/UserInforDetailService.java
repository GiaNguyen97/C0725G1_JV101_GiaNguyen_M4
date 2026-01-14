package org.example.soccer_manager.service.impl;

import org.example.soccer_manager.dto.account.UserInfoUserDetails;
import org.example.soccer_manager.entity.Account;
import org.example.soccer_manager.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInforDetailService implements UserDetailsService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepository.findByUsernameAndIsDeletedIsFalse(username);
        if(account == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        UserInfoUserDetails infoUserDetails = new UserInfoUserDetails(account);
        return infoUserDetails;
    }
}