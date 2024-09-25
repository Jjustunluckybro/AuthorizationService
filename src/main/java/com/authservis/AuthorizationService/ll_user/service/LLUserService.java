package com.authservis.AuthorizationService.ll_user.service;

import com.authservis.AuthorizationService.ll_user.models.LLUser;
import com.authservis.AuthorizationService.ll_user.repo.LLUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Слой бизнес логики для работы с пользователями
 */
@Component
@Service
public class LLUserService {

    private final LLUserRepo LLUserRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LLUserService(LLUserRepo LLUserRepo) {
        this.LLUserRepo = LLUserRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser(LLUser user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        LLUserRepo.saveAndFlush(user);
    }

    public Optional<LLUser> getUserById(int id) {
        return LLUserRepo.findById(id);
    }
}
