package br.com.helpme.helpmecore.user.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="ROLE")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_ROLE")
    private long idRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private RoleName roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}