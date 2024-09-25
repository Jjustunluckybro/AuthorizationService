package com.authservis.AuthorizationService.ll_user.repo;

import com.authservis.AuthorizationService.ll_user.models.LLUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Все что касается взаимодействия с бд пользователей
 * */
@Repository
@Service
public interface LLUserRepo extends JpaRepository<LLUser, Integer> {
    LLUser findByName(String name);
}
