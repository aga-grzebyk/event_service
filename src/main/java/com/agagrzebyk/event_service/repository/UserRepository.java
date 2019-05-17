package com.agagrzebyk.event_service.repository;

import com.agagrzebyk.event_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
