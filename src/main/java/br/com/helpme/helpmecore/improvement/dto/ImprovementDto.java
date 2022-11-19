package br.com.helpme.helpmecore.improvement.dto;

import br.com.helpme.helpmecore.improvement.model.Classification;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import br.com.helpme.helpmecore.user.model.User;

import java.util.Optional;

public class ImprovementDto {

    private String classification;
    private String title;
    private String message;

    private int likes;

    private int likedByUser;

    public ImprovementDto(Improvement improvement, User user) {
        this.classification = improvement.getClassification().name();
        this.title = improvement.getTitle();
        this.message = improvement.getMessage();
        this.likes = improvement.getLikes();

    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(final String classification) {
        this.classification = classification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Improvement toImprovement() {
        Optional<Classification> enumByClassificationTextOptional = Classification.getEnumByClassificationText(this.classification);
        return new Improvement(this.title,this.message,enumByClassificationTextOptional.get());
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(int likedByUser) {
        this.likedByUser = likedByUser;
    }
}
