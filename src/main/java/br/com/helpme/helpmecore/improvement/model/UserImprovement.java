package br.com.helpme.helpmecore.improvement.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_IMPROVEMENT")
public class UserImprovement {

    @Id
    @Column(name = "ID_USER_IMPROVEMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ID_USER")
    private long idUser;

    @Column(name = "ID_IMPROVEMENT")
    private long idImprovement;

    // 1 = LIKED AND 0 = DESLIKED
    @Column(name = "LIKED")
    private int liked;

    public UserImprovement() {
    }

    public UserImprovement(long idUser, long idImprovement) {
        this.idUser = idUser;
        this.idImprovement = idImprovement;
        this.liked = 1;
    }

    public long getId() {
        return id;
    }

    public long getIdUser() {
        return idUser;
    }

    public long getIdImprovement() {
        return idImprovement;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public void changeState(){
        if(this.liked == 0)
            setLiked(1);
        else
            setLiked(0);
    }
}
