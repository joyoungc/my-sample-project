package io.github.joyoungc.security.repository;

import io.github.joyoungc.security.domain.AdminUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/***
 * Created by Aiden Jeong on 2019.02.11
 */
public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {

    Optional<AdminUser> findByUserId(String userId);
}
