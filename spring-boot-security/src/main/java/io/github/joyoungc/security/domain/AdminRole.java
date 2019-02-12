package io.github.joyoungc.security.domain;

import lombok.Data;

import javax.persistence.*;

/***
 * Created by Aiden Jeong on 2019.02.11
 */
@Data
@Entity
@Table(name = "ADM_ROLE")
public class AdminRole {

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "ROLE_NM")
    private String roleName;

}
