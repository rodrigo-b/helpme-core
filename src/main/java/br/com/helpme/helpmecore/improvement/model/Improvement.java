package br.com.helpme.helpmecore.improvement.model;

import br.com.helpme.helpmecore.improvement.model.Classification;
import br.com.helpme.helpmecore.user.model.User;

import javax.persistence.*;

@Entity
public class Improvement {

    @Id
    private int id;
    private String title;
    private String message;
    private int likes;

    @Enumerated(EnumType.STRING)
    private Classification classification;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

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
}
