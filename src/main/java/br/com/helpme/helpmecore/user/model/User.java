package br.com.helpme.helpmecore.user.model;

import br.com.helpme.helpmecore.improvement.model.Improvement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USER")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private long idUser;

    private String name;
    private String email;

    private String password;

    private LocalDateTime creation;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable( name="USER_ROLES",
            joinColumns = @JoinColumn(name="ID_ROLE"),
            inverseJoinColumns = @JoinColumn(name="ID_USER"))
    private Set<Role> roles;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable( name = "USER_IMPROVEMENT",
                joinColumns = @JoinColumn(name="ID_IMPROVEMENT"),
                inverseJoinColumns = @JoinColumn(name="ID_USER")
    )
    private List<Improvement> improvementsLiked;
    @OneToMany(mappedBy="user")
    private Set<Improvement> improvements;

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Improvement> getImprovements() {
        return improvements;
    }

    public void setImprovements(Set<Improvement> improvements) {
        this.improvements = improvements;
    }

    public List<Improvement> getImprovementsLiked() {
        return improvementsLiked;
    }

    public void setImprovementsLiked(List<Improvement> improvementsLiked) {
        this.improvementsLiked = improvementsLiked;
    }
}
