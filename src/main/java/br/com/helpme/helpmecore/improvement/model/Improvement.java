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


}
