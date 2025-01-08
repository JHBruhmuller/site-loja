package com.projeto.loja.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.loja.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
}