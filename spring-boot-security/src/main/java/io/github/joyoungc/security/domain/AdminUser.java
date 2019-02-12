package io.github.joyoungc.security.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/***
 * Created by Aiden Jeong on 2019.02.11
 */

@Data
@Entity
@Table(name = "ADM_USER")
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADM_ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NM")
    private String userName;

    private String password;

    private boolean enabled;

    @Column(name = "PWD_AUTH_DT")
    private LocalDateTime passwordAuthDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ADM_USER_ROLES", joinColumns = @JoinColumn(name = "ADM_ID"), inverseJoinColumns =
    @JoinColumn(name = "ROLE_ID"))
    private List<AdminRole> roles;

}
