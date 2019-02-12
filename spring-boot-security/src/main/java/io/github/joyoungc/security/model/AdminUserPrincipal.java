package io.github.joyoungc.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/***
 * Created by Aiden Jeong on 2019.02.12
 */
@Getter
public class AdminUserPrincipal extends User {

    /*
     * UserPrincipal에 추가하고 싶은 정보들을 세팅
     */
    private String userName;

    public AdminUserPrincipal(String userId, String password, boolean enabled, Collection<?
            extends GrantedAuthority> authorities, String userName) {
        super(userId, password, enabled, true, true, true, authorities);
        this.userName = userName;
    }
}
