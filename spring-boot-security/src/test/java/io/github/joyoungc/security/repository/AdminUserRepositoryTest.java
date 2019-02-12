package io.github.joyoungc.security.repository;

import io.github.joyoungc.security.domain.AdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AdminUserRepositoryTest {

    @Autowired
    AdminUserRepository userRepository;

    @Test
    public void testJpa() {
        AdminUser adminUser = userRepository.findById(1L).orElse(null);
        assertThat(adminUser).isNotNull();
        assertThat(adminUser.getUserId()).isEqualTo("aiden");
        assertThat(adminUser.getRoles().size()).isEqualTo(2);
        System.out.println(adminUser.getRoles());
    }

}