package io.github.joyoungc.jpa.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.joyoungc.jpa.user.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {

}
