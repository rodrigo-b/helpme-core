package br.com.helpme.helpmecore.improvement.model;

import br.com.helpme.helpmecore.user.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "IMPROVEMENT")
public class Improvement {

    @Id
    @Column(name = "ID_IMPROVEMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String message;
    private int likes;

    @Enumerated(EnumType.STRING)
    private Classification classification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_USER", nullable=false)
    private User user;

    @ManyToMany(mappedBy = "improvementsLiked")
    private List<User> usersLikes;

    public Improvement() {
    }

    public Improvement(String title, String message, Classification classification) {
        this.title = title;
        this.message = message;
        this.classification = classification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public List<User> getUsersLikes() {
        return usersLikes;
    }
}
