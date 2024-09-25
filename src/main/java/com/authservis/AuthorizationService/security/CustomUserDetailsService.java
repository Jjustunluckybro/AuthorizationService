package com.authservis.AuthorizationService.security;

import com.authservis.AuthorizationService.ll_user.models.LLUser;
import com.authservis.AuthorizationService.ll_user.repo.LLUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LLUserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LLUser user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User
                .withUsername(user.getName())
                .password(user.getPassword())
                .build();
    }
}
