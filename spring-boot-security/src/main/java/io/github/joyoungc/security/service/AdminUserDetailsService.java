package io.github.joyoungc.security.service;

import io.github.joyoungc.security.domain.AdminRole;
import io.github.joyoungc.security.domain.AdminUser;
import io.github.joyoungc.security.model.AdminUserPrincipal;
import io.github.joyoungc.security.repository.AdminUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/***
 * Created by Aiden Jeong on 2019.02.12
 */
@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminUserRepository adminUserRepository;

    public AdminUserDetailsService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        AdminUser adminUser =
                adminUserRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("아이디가 일치하지 " +
                        "않습니다."));

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if (!CollectionUtils.isEmpty(adminUser.getRoles())) {
            for (AdminRole role : adminUser.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }

        return new AdminUserPrincipal(adminUser.getUserId(), adminUser.getPassword(), adminUser.isEnabled(),
                authorities, adminUser.getUserName());
    }

}
