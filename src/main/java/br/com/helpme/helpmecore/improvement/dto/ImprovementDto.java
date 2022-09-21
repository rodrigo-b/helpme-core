package br.com.helpme.helpmecore.improvement.dto;

import br.com.helpme.helpmecore.improvement.model.Classification;
import br.com.helpme.helpmecore.improvement.model.Improvement;

import java.util.Optional;

public class ImprovementDto {

    private String classification;
    private String title;
    private String message;

    public ImprovementDto() {
    }

    public ImprovementDto(Improvement improvement) {
        this.classification = improvement.getClassification().name();
        this.title = improvement.getTitle();
        this.message = improvement.getMessage();
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
}
