package ru.mii.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mii.springboot.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
